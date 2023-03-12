package com.example.testappeff.screens.signIn

import com.example.data.ProfileInfoEntity
import kotlinx.coroutines.flow.Flow

interface RepositorySignIn {

    suspend fun saveNewProfile(firstName: String, lastName: String, email: String)

    suspend fun checkProfileExist(
        firstName: String,
        lastName: String,
        email: String
    ): Flow<ProfileInfoEntity?>
}