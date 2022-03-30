package com.westmark.unsplash.networking

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.westmark.unsplash.database.DatabasePhoto
import com.westmark.unsplash.database.PhotoDao
import com.westmark.unsplash.database.RemoteKeyDao
import com.westmark.unsplash.presentation.data.PhotoEntity
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class UnsplashRemoteMediator @Inject constructor(
    private val api: UnsplashApi,
    private val photoDao: PhotoDao,
    private val remoteKeyDao: RemoteKeyDao,
    private val database: DatabasePhoto
) : RemoteMediator<Int, PhotoEntity>() {
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.SKIP_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PhotoEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKey = getCurrentPosition(state)
                val key = remoteKey?.nextKey?.minus(1) ?: UNSPLASH_STARTING_PAGE_INDEX
                key
            }
            LoadType.PREPEND -> {
                val remoteKey = getFirstPosition(state)
                val prevKey = remoteKey?.prevKey ?: return MediatorResult.Success(
                    endOfPaginationReached = remoteKey != null
                )
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKey = getLastPosition(state)
                val nextKey = remoteKey?.nextKey ?: return MediatorResult.Success(
                    endOfPaginationReached = remoteKey != null
                )
                nextKey
            }
        }
        return try {
            val photos = api.getPhotos(page, 10)
            val endPaginationReached = photos.isEmpty()
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeyDao.deleteByQuery()
                    photoDao.clearAll()
                }
            }
                val prevKey = if (page == UNSPLASH_STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endPaginationReached) null else page + 1
                val keys = photos.map {
                    RemoteKey(
                        id = it.id,
                        nextKey = nextKey,
                        prevKey = prevKey
                    )
                }
                photoDao.insertPhotoDatabase(photos)
                remoteKeyDao.insertOrReplace(keys)

            return MediatorResult.Success(endOfPaginationReached = endPaginationReached)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getLastPosition(state: PagingState<Int, PhotoEntity>): RemoteKey? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { photo ->
            remoteKeyDao.remoteKeyByQuery(photo.id)
        }
    }

    private suspend fun getFirstPosition(
        state: PagingState<Int, PhotoEntity>
    ): RemoteKey? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { photo ->
            remoteKeyDao.remoteKeyByQuery(photo.id)
        }
    }

    private suspend fun getCurrentPosition(state: PagingState<Int, PhotoEntity>): RemoteKey? {
        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.id?.let { id ->
                remoteKeyDao.remoteKeyByQuery(id)
            }
        }
    }
}
