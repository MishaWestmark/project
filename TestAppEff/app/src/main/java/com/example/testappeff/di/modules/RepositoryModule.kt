package com.example.testappeff.di.modules

import com.example.data.Repository
import com.example.data.RepositoryImpl
import com.example.testappeff.screens.signIn.RepositorySignIn
import com.example.testappeff.screens.signIn.RepositorySignInImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun providesSignInRepository(impl: RepositorySignInImpl): RepositorySignIn {
        return impl
    }

    @Provides
    fun providesRepository(impl: RepositoryImpl): Repository {
        return impl
    }


//    @Provides
//    fun providesActionRepository(sharedPreferences: SharedPreferences): RepositoryActionImpl {
//        return RepositoryActionImpl(sharedPreferences)
//    }
//
//    @Provides
//    fun providesCollectionsRepository(impl: RepositoryCollectionsImpl): RepositoryCollections {
//        return impl
//    }
//
//    @Provides
//    fun provideActionRepository(impl: RepositoryActionImpl): RepositoryAction {
//        return impl
//    }
//
//    @Provides
//    fun providesRepositoryProfile(impl: RepositoryProfileImpl): RepositoryProfile {
//        return impl
//    }
}