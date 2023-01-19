package com.example.apptest.network

import com.example.apptest.CardInformation
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BinApi {
    @GET("/{number}")
    fun getInfoCard(
        @Path(value = "number") number: String
    ): Call<CardInformation>

}