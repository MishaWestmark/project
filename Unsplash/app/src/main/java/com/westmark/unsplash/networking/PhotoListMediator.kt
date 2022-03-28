package com.westmark.unsplash.networking

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
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
        val pageKey = getPageKeyData(state, loadType)
        val page = when (pageKey) {
            is MediatorResult.Success -> {
                return pageKey
            }
            else -> {
                pageKey as Int
            }
        }
        return try {
            val response = api.getPhotos(page)
            Log.d("response", response.toString())
            val isListEmpty = response.isEmpty()
//            database.withTransaction {
            if (loadType == LoadType.REFRESH) {
                remoteKeyDao.deleteByQuery()
                photoDao.clearAll()
            }
            val prevKey = if (page == 1) null else page - 1
            val nextkey = if (isListEmpty) null else page + 1
            val keys = response.map {
                RemoteKey(it.id, nextkey, prevKey)
            }
            photoDao.insertPhotoDatabase(response)
            remoteKeyDao.insertOrReplace(keys)
//            }
            return MediatorResult.Success(endOfPaginationReached = isListEmpty)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getPageKeyData(
        state: PagingState<Int, PhotoEntity>,
        loadType: LoadType
    ): Any {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getCurrentPosition(state)
                val current = remoteKeys?.nextKey?.minus(1)
                if (current != null) {
                    return current
                } else {
                    UNSPLASH_STARTING_PAGE_INDEX
                }
            }
            LoadType.PREPEND -> {
                val remoteKey = getFirstPosition(state)
                val prevKey =
                    remoteKey?.prevKey ?: MediatorResult.Success(endOfPaginationReached = true)
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKey = getLastPosition(state)
                val nextKey = remoteKey?.nextKey
                return if (nextKey != null) nextKey else MediatorResult.Success(
                    endOfPaginationReached = false
                )
            }
        }
    }

    private suspend fun getLastPosition(state: PagingState<Int, PhotoEntity>): RemoteKey? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull().let { photo ->
            remoteKeyDao.remoteKeyByQuery(photo?.id.toString())
        }
    }

    private suspend fun getFirstPosition(
        state: PagingState<Int, PhotoEntity>
    ): RemoteKey? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull().let { photo ->
            remoteKeyDao.remoteKeyByQuery(photo?.id.toString())
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






