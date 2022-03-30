package com.westmark.unsplash.presentation.ui.photoList.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.westmark.unsplash.database.DatabasePhoto
import com.westmark.unsplash.database.PhotoDao
import com.westmark.unsplash.database.RemoteKeyDao
import com.westmark.unsplash.networking.UnsplashApi
import com.westmark.unsplash.networking.CollectionsPagingSource
import com.westmark.unsplash.networking.UnsplashRemoteMediator
import com.westmark.unsplash.presentation.data.PhotoDetail
import com.westmark.unsplash.presentation.data.PhotoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryPhotoListImpl @Inject constructor(
    private val api: UnsplashApi,
    private val photoDao: PhotoDao,
    private val remoteDao: RemoteKeyDao,
    private val database: DatabasePhoto
) : RepositoryPhotoList {
    @OptIn(ExperimentalPagingApi::class)
    override fun getPhotoResult(): Flow<PagingData<PhotoEntity>> {
        val pagingSourceFactory = { photoDao.getPhotoDatabase() }
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            remoteMediator = UnsplashRemoteMediator(api, photoDao, remoteDao, database),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

   override suspend fun getPhotoById(id: String): PhotoDetail {
        return api.getPhotoById(id)
    }
}