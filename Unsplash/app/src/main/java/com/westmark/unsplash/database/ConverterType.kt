package com.westmark.unsplash.database

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.westmark.unsplash.presentation.data.*

class ConverterType {
    @TypeConverter
    fun stringToUser(input: String?): PhotoEntity.User? =
        input?.let { Moshi.Builder().build().adapter(PhotoEntity.User::class.java).fromJson(it) }

    @TypeConverter
    fun userToString(input: PhotoEntity.User): String? =
        Moshi.Builder().build().adapter(PhotoEntity.User::class.java).toJson(input)

    @TypeConverter
    fun stringToUrls(input: String?): PhotoEntity.Urls? =
        input?.let { Moshi.Builder().build().adapter(PhotoEntity.Urls::class.java).fromJson(it) }

    @TypeConverter
    fun urlsToString(input: PhotoEntity.Urls): String? =
        Moshi.Builder().build().adapter(PhotoEntity.Urls::class.java).toJson(input)

    @TypeConverter
    fun stringToProfileUrls(input: String?): PhotoEntity.User.ProfileUrlsImage? =
        input?.let {
            Moshi.Builder().build().adapter(PhotoEntity.User.ProfileUrlsImage::class.java).fromJson(it)
        }

    @TypeConverter
    fun urlsToString(input: PhotoEntity.User.ProfileUrlsImage): String? =
        Moshi.Builder().build().adapter(PhotoEntity.User.ProfileUrlsImage::class.java).toJson(input)


}