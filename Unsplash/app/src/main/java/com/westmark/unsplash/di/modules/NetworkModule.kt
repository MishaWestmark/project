package com.westmark.unsplash.di.modules

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.westmark.unsplash.authentication.AuthCustomInterceptor
import com.westmark.unsplash.authentication.data.AuthConfig
import com.westmark.unsplash.networking.UnsplashApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideOkHttpClient(interceptors: Interceptor): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addNetworkInterceptor(interceptors)
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return client
            .followRedirects(true)
            .build()
    }

    @Provides
    fun provideHeaderInterceptor(authInterceptor: AuthCustomInterceptor): Interceptor {
        return authInterceptor
    }

//    @Provides
//    fun provideLoggingInterceptor(): Interceptor {
//        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(AuthConfig.API_URL)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): UnsplashApi {
        return retrofit.create()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }


}