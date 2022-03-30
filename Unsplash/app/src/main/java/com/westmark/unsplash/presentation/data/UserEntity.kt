package com.westmark.unsplash.presentation.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserEntity(
    @Json(name = "id")
    val id: String,
    @Json(name = "username")
    val userName: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "bio")
    val bio: String?,
    @Json(name = "location")
    val location: String?,
    @Json(name = "downloads")
    val downloads: Int,
    @Json(name = "email")
    val email: String?,
    @Json(name= "profile_image")
    val urlImageProfile: ProfileImage,
    @Json(name= "total_likes")
    val totalLikes: Int,
    @Json(name= "total_collections")
    val totalCollections: Int,
    @Json(name= "total_photos")
    val totalPhotos: Int
) {
    @JsonClass(generateAdapter = true)
    data class ProfileImage(
        @Json(name = "medium")
        val urlMedium: String
    )
}
