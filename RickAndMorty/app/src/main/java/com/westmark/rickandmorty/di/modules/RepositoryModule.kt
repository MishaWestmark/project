package com.westmark.rickandmorty.di.modules

import com.westmark.rickandmorty.presentation.data.Repository
import com.westmark.rickandmorty.presentation.data.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun providesPhotoRepository(impl: RepositoryImpl): Repository {
        return impl
    }
}