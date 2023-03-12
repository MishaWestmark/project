package com.example.testappeff.screens.signIn

import com.example.data.ProfileDao
import com.example.data.ProfileInfoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class RepositorySignInImpl @Inject constructor(private val db: ProfileDao) : RepositorySignIn {

    override suspend fun saveNewProfile(firstName: String, lastName: String, email: String) {
        withContext(Dispatchers.IO) {
            val newProfile =
                ProfileInfoEntity(
                    firstname = firstName,
                    lastName = lastName,
                    email = email
                )
            db.insertProfile(newProfile)
        }
    }

    override suspend fun checkProfileExist(
        firstName: String,
        lastName: String,
        email: String
    ): Flow<ProfileInfoEntity?> {
        return withContext(Dispatchers.IO) {
            db.checkProfileInDatabase(firstName, lastName, email)
        }
    }
}