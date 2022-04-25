package com.westmark.rickandmorty.networking

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.westmark.rickandmorty.models.Response
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharactersPagingSource @Inject constructor(
    private val api: CharactersApi
) : PagingSource<Int, Response.Character>() {
    override fun getRefreshKey(state: PagingState<Int, Response.Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)

        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Response.Character> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = api.getCharacters(position).results
            val nextKey = if (response.isEmpty()) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = response,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    companion object {
        const val STARTING_PAGE_INDEX = 1
        const val NETWORK_PAGE_SIZE = 30
    }
}