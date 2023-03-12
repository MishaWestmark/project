package com.example.data.network

import android.graphics.Color
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailItem(
    @Json(name = "name")
    val name: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "rating")
    val rating: String,
    @Json(name = "number_of_reviews")
    val numberOfReviews: String,
    @Json(name = "price")
    val price: String,
    @Json(name = "colors")
    val listColors: List<String>,
    @Json(name = "image_urls")
    val imageUrls: List<String>

) {
//    @JsonClass(generateAdapter = true)
//    data class ColorItem(
//        val color: String
//    )
//    @JsonClass(generateAdapter = true)
//    data class UrlItem (
//        val urlImage: String
//            )
}
