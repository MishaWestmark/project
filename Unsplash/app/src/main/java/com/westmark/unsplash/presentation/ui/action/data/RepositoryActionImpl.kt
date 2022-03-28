package com.westmark.unsplash.presentation.ui.action.data

import android.content.SharedPreferences
import javax.inject.Inject

class RepositoryActionImpl @Inject constructor(private val sharedPref: SharedPreferences) :
    RepositoryAction {
    override fun addFlagAfterFirstEntry() {
        sharedPref.edit()
            .putBoolean(FIRST_ENTRY, true)
            .apply()
    }

    override fun isFirstEntry(): Boolean {
        return sharedPref.contains(FIRST_ENTRY)
    }

    override fun containsAccessToken(): Boolean {
        return sharedPref.contains(ACCESS_TOKEN)
    }

    companion object {
        private const val ACCESS_TOKEN = "access token"
        private const val FIRST_ENTRY = "first_entry"
    }

}