package com.westmark.unsplash.presentation.ui.profile.data

import androidx.paging.PagingData
import com.westmark.unsplash.presentation.data.PhotoEntity
import com.westmark.unsplash.presentation.data.UserEntity
import kotlinx.coroutines.flow.Flow

interface RepositoryProfile {
    suspend fun getUserInfo(): UserEntity
    fun getUserPhotos(userName: String): Flow<PagingData<PhotoEntity>>
    fun getUserLikedPhotos(userName: String): Flow<PagingData<PhotoEntity>>
}