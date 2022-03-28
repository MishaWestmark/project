package com.westmark.unsplash.di.modules

import android.app.Application
import com.westmark.unsplash.authentication.data.AuthRepository
import com.westmark.unsplash.authentication.data.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.openid.appauth.AuthorizationService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {
    @Provides
    @Singleton
    fun providesAuthService(application: Application): AuthorizationService {
        return AuthorizationService(application)
    }

    @Provides
    fun providesAuthRepository(impl: AuthRepositoryImpl): AuthRepository {
        return impl
    }
}