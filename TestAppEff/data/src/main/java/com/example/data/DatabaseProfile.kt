package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProfileInfoEntity::class], version = DatabasePhoto.DB_VERSION)
abstract class DatabasePhoto : RoomDatabase() {
    abstract fun profileDao(): ProfileDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "profile_database"
    }
}