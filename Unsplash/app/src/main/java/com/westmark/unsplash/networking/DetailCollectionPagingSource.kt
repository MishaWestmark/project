package com.westmark.unsplash.networking

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.westmark.unsplash.presentation.data.CollectionDetail
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DetailCollectionPagingSource @Inject constructor(
    private val queryId: String,
    private val api: UnsplashApi
) : PagingSource<Int, CollectionDetail>() {
    override fun getRefreshKey(state: PagingState<Int, CollectionDetail>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CollectionDetail> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
        return try {
            val response = api.getCollectionPhotoById(queryId, position, params.loadSize)
            val nextKey = if (response.isEmpty()) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = response,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    companion object {
        private const val UNSPLASH_STARTING_PAGE_INDEX = 1
        const val NETWORK_PAGE_SIZE = 30
    }
}