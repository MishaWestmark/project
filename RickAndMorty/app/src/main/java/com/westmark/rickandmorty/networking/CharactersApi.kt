package com.westmark.rickandmorty.networking

import com.westmark.rickandmorty.models.CharacterDetail
import com.westmark.rickandmorty.models.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApi {
    @GET("/api/character")
    suspend fun getCharacters(
        @Query(value = "page") page: Int
    ): Response

    @GET("/api/character/{id}/")
    suspend fun getDetailInfo(
        @Path("id") id: String
    ): CharacterDetail
}