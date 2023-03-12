package com.example.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.random.Random

@JsonClass(generateAdapter = true)
data class FlashSaleItems(
   @Json(name = "flash_sale")
   val item: List<Item>
) {
    @JsonClass(generateAdapter = true)
    data class Item(
        val id: Int = Random.nextInt(),
        @Json(name = "category")
        val category: String,
        @Json(name = "name")
        val name: String,
        @Json(name = "price")
        val price: String,
        @Json(name = "discount")
        val discount: String,
        @Json(name = "image_url")
        val imageUrl: String
    )

}
