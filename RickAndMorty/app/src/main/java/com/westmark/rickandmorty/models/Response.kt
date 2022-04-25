package com.westmark.rickandmorty.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Response(
    @Json(name = "results")
    val results: List<Character>
) {
    @JsonClass(generateAdapter = true)
    data class Character(
        @Json(name = "id")
        val id: Int,
        @Json(name = "name")
        val name: String,
        @Json(name = "species")
        val species: String,
        @Json(name = "gender")
        val gender: String,
        @Json(name = "image")
        val image: String
    )
}