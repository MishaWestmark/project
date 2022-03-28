package com.westmark.unsplash.presentation.ui.photoList.data

import androidx.paging.PagingData
import com.westmark.unsplash.presentation.data.PhotoDetail
import com.westmark.unsplash.presentation.data.PhotoEntity
import kotlinx.coroutines.flow.Flow

interface RepositoryPhotoList {
    fun getPhotoResult(): Flow<PagingData<PhotoEntity>>
    suspend fun getPhotoById(id: String): PhotoDetail
}