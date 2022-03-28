package com.westmark.unsplash.presentation.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.westmark.unsplash.database.PhotoContract

@JsonClass(generateAdapter = true)
@Entity(tableName = PhotoContract.TABLE_NAME)
data class PhotoEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = PhotoContract.Columns.ID)
    @Json(name = "id")
    val id: String,
    @ColumnInfo(name = PhotoContract.Columns.NUMBER_LIKES)
    @Json(name = "likes")
    val likes: Int,
    @ColumnInfo(name = PhotoContract.Columns.IS_LIKED)
    @Json(name = "liked_by_user")
    val isLiked: Boolean,
    @Json(name = "user")
    @ColumnInfo(name = PhotoContract.Columns.USER)
    val user: User,
    @Json(name = "urls")
    @ColumnInfo(name = PhotoContract.Columns.URLS)
    val urls: Urls

) {
    @JsonClass(generateAdapter = true)
    data class Urls(
        @ColumnInfo(name = PhotoContract.Columns.URL_PHOTO)
        @Json(name = "regular")
        val urlRegular: String
    )

    @JsonClass(generateAdapter = true)
    data class User(
        @ColumnInfo(name = PhotoContract.Columns.ID)
        @Json(name = "id")
        val id: String,
        @ColumnInfo(name = PhotoContract.Columns.USER_ACCOUNT)
        @Json(name = "username")
        val accountName: String,
        @ColumnInfo(name = PhotoContract.Columns.USER_NAME)
        @Json(name = "name")
        val name: String,
        @ColumnInfo(name = PhotoContract.Columns.URL_PHOTO)
        @Json(name = "profile_image")
        val profileImage: ProfileUrlsImage
    ) {
        @JsonClass(generateAdapter = true)
        data class ProfileUrlsImage(
            @ColumnInfo(name = PhotoContract.Columns.USER_URL_PHOTO_PROFILE)
            @Json(name = "small")
            val urlSmall: String
        )
    }
}








