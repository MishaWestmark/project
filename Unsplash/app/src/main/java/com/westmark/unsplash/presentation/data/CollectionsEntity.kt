package com.westmark.unsplash.presentation.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CollectionsEntity(
    @Json(name = "id")
    val id: String,
    @Json(name = "title")
    val title: String?,
    @Json(name = "total_photos")
    val totalPhotos: Int,
    @Json(name = "user")
    val user: User,
    @Json(name = "cover_photo")
    val coverPhotoUrl: CoverPhoto

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
        val profileImage: ProfileImage
    ) {
        @JsonClass(generateAdapter = true)
        data class ProfileImage(
            @Json(name = "small")
            val urlSmall: String
        )
    }

    @JsonClass(generateAdapter = true)
    data class CoverPhoto(
        @Json(name = "urls")
        val photoUrl: Urls
    ){
        @JsonClass(generateAdapter = true)
        data class Urls(
            @Json(name = "regular")
            val urlRegular: String
        )
    }
}