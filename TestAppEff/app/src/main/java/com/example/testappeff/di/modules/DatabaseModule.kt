package com.example.testappeff.di.modules

import android.app.Application
import androidx.room.Room
import com.example.data.DatabasePhoto
import com.example.data.ProfileDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun providesDatabase(application: Application): DatabasePhoto {
        return Room.databaseBuilder(application, DatabasePhoto::class.java, DatabasePhoto.DB_NAME)
            .build()
    }

    @Singleton
    @Provides
    fun providesProfileDao(db: DatabasePhoto): ProfileDao {
        return db.profileDao()
    }


}