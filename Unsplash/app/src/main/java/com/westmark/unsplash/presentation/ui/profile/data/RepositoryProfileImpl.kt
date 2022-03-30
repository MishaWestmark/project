package com.westmark.unsplash.presentation.ui.profile.data

import androidx.paging.*
import com.westmark.unsplash.networking.CollectionsPagingSource
import com.westmark.unsplash.networking.UnsplashApi
import com.westmark.unsplash.presentation.data.PhotoEntity
import com.westmark.unsplash.presentation.data.UserEntity
import com.westmark.unsplash.presentation.ui.profile.viewPager.network.ProfileLikedPhotosPagingSource
import com.westmark.unsplash.presentation.ui.profile.viewPager.network.ProfilePhotosPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryProfileImpl @Inject constructor(private val api: UnsplashApi) : RepositoryProfile {

    override suspend fun getUserInfo(): UserEntity {
        return api.getUserProfile()
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getUserPhotos(userName: String): Flow<PagingData<PhotoEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = CollectionsPagingSource.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ProfilePhotosPagingSource(userName, api) }
        ).flow
    }
    @OptIn(ExperimentalPagingApi::class)
    override fun getUserLikedPhotos(userName: String): Flow<PagingData<PhotoEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = CollectionsPagingSource.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ProfileLikedPhotosPagingSource(userName, api) }
        ).flow
    }
}