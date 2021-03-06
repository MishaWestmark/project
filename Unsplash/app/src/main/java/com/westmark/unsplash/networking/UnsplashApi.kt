package com.westmark.unsplash.networking

import com.westmark.unsplash.presentation.data.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApi {
    @GET("/photos")
    suspend fun getPhotos(
        @Query(value = "page") page: Int,
        @Query(value = "per_page") perPage: Int
    ): List<PhotoEntity>

    @GET("/photos/{id}/")
    suspend fun getPhotoById(
        @Path("id") id: String
    ): PhotoDetail

    @GET("/collections")
    suspend fun getCollections(
        @Query(value = "page") page: Int,
        @Query(value = "per_page") perPage: Int
    ): List<CollectionsEntity>

    @GET("/collections/{id}/photos/")
    suspend fun getCollectionPhotoById(
        @Path("id") id: String,
        @Query(value = "page") page: Int,
        @Query(value = "per_page") perPage: Int
    ): List<CollectionDetail>

    @GET("/me")
    suspend fun getUserProfile(): UserEntity

    @GET("/users/{username}/photos")
    suspend fun getUserPhotos(
        @Path("username") userName: String,
        @Query(value = "page") page: Int,
        @Query(value = "per_page") perPage: Int
    ): List<PhotoEntity>

    @GET("/users/{username}/likes")
    suspend fun getUserLikedPhotos(
        @Path("username") userName: String,
        @Query(value = "page") page: Int,
        @Query(value = "per_page") perPage: Int
    ): List<PhotoEntity>


}