package com.westmark.rickandmorty.presentation.data

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.westmark.rickandmorty.models.CharacterDetail
import com.westmark.rickandmorty.models.Response
import com.westmark.rickandmorty.networking.CharactersApi
import com.westmark.rickandmorty.networking.CharactersPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val api: CharactersApi
) : Repository {
    override fun getCharactersResults(): Flow<PagingData<Response.Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = CharactersPagingSource.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CharactersPagingSource(api) }
        ).flow
    }

    override suspend fun getDetailCharacter(id: String): CharacterDetail {
        val character = api.getDetailInfo(id)
        Log.e("repository", "$character")
        return character


    }
}