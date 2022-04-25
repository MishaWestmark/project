package com.westmark.rickandmorty.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterDetail(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "status")
    val status: String,
    @Json(name = "species")
    val species: String,
    @Json(name = "gender")
    val gender: String,
    @Json(name = "image")
    val image: String,
    @Json(name = "location")
    val location: Location,
    @Json(name = "episode")
    val episode: List<String>

) {
    @JsonClass(generateAdapter = true)
    data class Location(
        @Json(name = "name")
        val name: String
    )
}