package com.westmark.unsplash.presentation.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoDetail(
    @Json(name = "id")
    val id: String,
    @Json(name = "likes")
    val likes: Int,
    @Json(name = "liked_by_user")
    val likeByUser: Boolean,
    @Json(name = "exif")
    val exif: Exif,
    @Json(name = "location")
    val location: Location,
    @Json(name = "tags")
    val tags: List<Tags>,
    @Json(name = "urls")
    val urls: Urls,
    @Json(name = "user")
    val user: User

){
    @JsonClass(generateAdapter = true)
    data class User(
        @Json(name = "username")
        val userName: String,
        @Json(name = "name")
        val name: String,
        @Json(name = "profile_image")
        val profileImage: ProfileImage,
        @Json(name = "bio")
        val bio: String?
    ){
        @JsonClass(generateAdapter = true)
        data class ProfileImage(
            @Json(name = "small")
            val imageUrlSmall: String
        )
    }
    @JsonClass(generateAdapter = true)
    data class Urls(
        @Json(name = "regular")
        val urlRegular: String
    )
    @JsonClass(generateAdapter = true)
    data class Exif(
        @Json(name = "make")
        val made: String?,
        @Json(name = "model")
        val model: String?,
        @Json(name = "exposure_time")
        val exposureTime: String?,
        @Json(name = "aperture")
        val aperture: String?,
        @Json(name = "focal_length")
        val focalLength: String?,
        @Json(name = "iso")
        val iso: String?
    )
    @JsonClass(generateAdapter = true)
    data class Location(
        @Json(name = "city")
        val city: String?,
        @Json(name = "country")
        val country: String?
    )
    @JsonClass(generateAdapter = true)
    data class Tags(
        @Json(name = "title")
        val title: String?
    )

}