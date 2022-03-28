package com.westmark.unsplash.presentation.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CollectionDetail(
    @Json(name = "id")
    val id: String,
    @Json(name = "likes")
    val likes: String,
    @Json(name = "liked_by_user")
    val likedByUser: Boolean,
    @Json(name = "user")
    val user: User,
    @Json(name = "urls")
    val urlPhoto: Urls
) {
    @JsonClass(generateAdapter = true)
    data class User(
        @Json(name = "id")
        val id: String,
        @Json(name = "username")
        val userName: String,
        @Json(name = "name")
        val name: String,
        @Json(name = "profile_image")
        val profileImageUrl: ProfileImage
    ) {
        @JsonClass(generateAdapter = true)
        data class ProfileImage(
            @Json(name = "small")
            val urlSmall: String
        )
    }

    @JsonClass(generateAdapter = true)
    data class Urls(
        @Json(name = "regular")
        val urlRegular: String
    )
}