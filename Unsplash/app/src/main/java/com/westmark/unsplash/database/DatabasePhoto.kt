package com.westmark.unsplash.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.westmark.unsplash.presentation.data.PhotoEntity
import com.westmark.unsplash.networking.RemoteKey

@Database(entities = [PhotoEntity::class, RemoteKey::class], version = DatabasePhoto.DB_VERSION)
@TypeConverters(ConverterType::class)
abstract class DatabasePhoto : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
    abstract fun remoteDao(): RemoteKeyDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "photo-database"
    }
}