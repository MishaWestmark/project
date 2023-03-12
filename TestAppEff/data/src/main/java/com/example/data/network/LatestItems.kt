package com.example.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.random.Random

@JsonClass(generateAdapter = true)
data class LatestItems(
    @Json(name = "latest")
    val items: List<Item>
) {
    @JsonClass(generateAdapter = true)
    data class Item(
        val id: Int = Random(100000).nextInt(1000000),
            @Json(name = "category")
            val category: String,
        @Json(name = "name")
        val name: String,
        @Json(name = "price")
        val price: String,
        @Json(name = "image_url")
        val imageUrl: String
    )
}
