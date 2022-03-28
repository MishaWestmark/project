package com.westmark.unsplash.database

object PhotoContract {
    const val TABLE_NAME = "photos_Unsplash"

    object Columns {
        const val ID = "id"
        const val URL_PHOTO = "url photo"
        const val USER = "user"
        const val URLS = "urls"
        const val USER_NAME = "user name"
        const val USER_ACCOUNT = "user account"
        const val USER_URL_PHOTO_PROFILE = "user profile photo"
        const val NUMBER_LIKES = "number of likes"
        const val IS_LIKED = "is liked"
    }
}