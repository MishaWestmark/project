package com.westmark.unsplash.presentation.ui.collections.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.westmark.unsplash.networking.CollectionsPagingSource
import com.westmark.unsplash.networking.DetailCollectionPagingSource
import com.westmark.unsplash.networking.UnsplashApi
import com.westmark.unsplash.presentation.data.CollectionDetail
import com.westmark.unsplash.presentation.data.CollectionsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryCollectionsImpl @Inject constructor(private val api: UnsplashApi) :
    RepositoryCollections {
    override fun getCollectionResult(): Flow<PagingData<CollectionsEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = CollectionsPagingSource.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CollectionsPagingSource(api = api) }
        ).flow
    }

    override fun getCollectionPhotos(id: String): Flow<PagingData<CollectionDetail>> {
        return Pager(
            config = PagingConfig(
                pageSize = DetailCollectionPagingSource.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { DetailCollectionPagingSource(id, api) }
        ).flow
    }
}