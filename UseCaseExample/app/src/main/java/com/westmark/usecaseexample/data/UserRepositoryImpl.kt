package com.westmark.usecaseexample.data

import android.content.Context
import com.westmark.usecaseexample.domain.models.SaveUserNameParams
import com.westmark.usecaseexample.domain.models.UserName
import com.westmark.usecaseexample.domain.repository.UserRepository

class UserRepositoryImpl(private val context: Context) : UserRepository {
    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveName(params: SaveUserNameParams): Boolean {
        sharedPreferences.edit().putString(KEY_FIRST_NAME, params.name).apply()
        return true
    }

    override fun getName(): UserName {
        val firstname = sharedPreferences.getString(KEY_FIRST_NAME, "") ?: ""
        val lastname = sharedPreferences.getString(KEY_LAST_NAME, "fghfghf") ?: "Westmark"
        return UserName(firstName = firstname, lastName = lastname)
    }

    companion object {
        private const val SHARED_PREFS_NAME = "shared_prefs"
        private const val KEY_FIRST_NAME = "first name"
        private const val KEY_LAST_NAME = "last name"
    }
}