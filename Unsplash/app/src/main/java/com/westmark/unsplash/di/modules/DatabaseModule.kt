package com.westmark.unsplash.di.modules

import android.app.Application
import androidx.room.Room
import com.westmark.unsplash.database.DatabasePhoto
import com.westmark.unsplash.database.PhotoDao
import com.westmark.unsplash.database.RemoteKeyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun providesDatabase(application: Application): DatabasePhoto {
        return Room.databaseBuilder(application, DatabasePhoto::class.java, DatabasePhoto.DB_NAME)
            .build()
    }

    @Provides
    fun providesPhotoDao(db: DatabasePhoto): PhotoDao {
        return db.photoDao()
    }

    @Provides
    fun providesRemoteDao(db: DatabasePhoto): RemoteKeyDao {
        return db.remoteDao()
    }
}