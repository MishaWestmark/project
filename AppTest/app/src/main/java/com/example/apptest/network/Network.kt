package com.example.apptest.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

const val API_URL = "https://lookup.binlist.net/"

object Network {
    private val client = OkHttpClient.Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(API_URL)
        .client(client)
        .build()
    val BinApi: BinApi
        get() = retrofit.create()

}