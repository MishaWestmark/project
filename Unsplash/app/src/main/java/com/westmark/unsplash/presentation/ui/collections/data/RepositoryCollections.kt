package com.westmark.unsplash.presentation.ui.collections.data

import androidx.paging.PagingData
import com.westmark.unsplash.presentation.data.CollectionDetail
import com.westmark.unsplash.presentation.data.CollectionsEntity
import kotlinx.coroutines.flow.Flow

interface RepositoryCollections {
    fun getCollectionResult(): Flow<PagingData<CollectionsEntity>>
    fun getCollectionPhotos(id: String): Flow<PagingData<CollectionDetail>>
}