package com.westmark.unsplash.di.modules

import android.content.SharedPreferences
import com.westmark.unsplash.presentation.ui.action.data.RepositoryAction
import com.westmark.unsplash.presentation.ui.action.data.RepositoryActionImpl
import com.westmark.unsplash.presentation.ui.collections.data.RepositoryCollections
import com.westmark.unsplash.presentation.ui.collections.data.RepositoryCollectionsImpl
import com.westmark.unsplash.presentation.ui.photoList.data.RepositoryPhotoList
import com.westmark.unsplash.presentation.ui.photoList.data.RepositoryPhotoListImpl
import com.westmark.unsplash.presentation.ui.profile.data.RepositoryProfile
import com.westmark.unsplash.presentation.ui.profile.data.RepositoryProfileImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun providesPhotoRepository(impl: RepositoryPhotoListImpl): RepositoryPhotoList {
        return impl
    }

    @Provides
    fun providesActionRepository(sharedPreferences: SharedPreferences): RepositoryActionImpl {
        return RepositoryActionImpl(sharedPreferences)
    }

    @Provides
    fun providesCollectionsRepository(impl: RepositoryCollectionsImpl): RepositoryCollections {
        return impl
    }

    @Provides
    fun provideActionRepository(impl: RepositoryActionImpl): RepositoryAction {
        return impl
    }
    @Provides
    fun providesRepositoryProfile(impl: RepositoryProfileImpl): RepositoryProfile {
        return impl
    }
}