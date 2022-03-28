package com.westmark.unsplash.di.modules

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {
    @Provides
    @Singleton
    fun provideSharedPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    fun providesContext(@ApplicationContext context: Context): Context {
        return context
    }

    companion object {
        private const val SHARED_PREFS_NAME = "shared_prefs"
    }
}