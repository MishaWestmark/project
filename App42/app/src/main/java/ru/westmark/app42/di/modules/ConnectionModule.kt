package ru.westmark.app42.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ConnectionModule {
    @Provides
    fun providesContext(@ApplicationContext context: Context): Context {
        return context
    }
}