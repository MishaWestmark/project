package com.example.data.network

import retrofit2.Call
import retrofit2.http.GET

interface ShopApi {
    @GET(value = "/v3/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    fun getLatest(): Call<LatestItems>
    @GET(value = "/v3/a9ceeb6e-416d-4352-bde6-2203416576ac")
    fun getFlashSale(): Call<FlashSaleItems>
    @GET(value = "v3/f7f99d04-4971-45d5-92e0-70333383c239")
    fun getDetailInfo(): Call<DetailItem>
}