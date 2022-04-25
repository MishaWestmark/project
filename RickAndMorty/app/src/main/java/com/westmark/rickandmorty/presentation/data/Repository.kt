package com.westmark.rickandmorty.presentation.data

import androidx.paging.PagingData
import com.westmark.rickandmorty.models.CharacterDetail
import com.westmark.rickandmorty.models.Response
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getCharactersResults(): Flow<PagingData<Response.Character>>
    suspend fun getDetailCharacter(id: String): CharacterDetail
}