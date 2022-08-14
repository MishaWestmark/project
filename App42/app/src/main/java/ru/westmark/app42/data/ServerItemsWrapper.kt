package ru.westmark.app42.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ServerItemsWrapper<T>(
    @Json(name = "items")
    val items: List<T>
)
