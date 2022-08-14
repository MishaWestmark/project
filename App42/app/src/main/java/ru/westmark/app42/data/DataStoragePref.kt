package ru.westmark.app42.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoragePref(private val context: Context) {
    // to make sure there's only one instance
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userFilter")
        val USER_FILTER_KEY = booleanPreferencesKey("user_filter")
        val USER_FILTER_TYPE = stringPreferencesKey("user_filter_type")
        val USER_FILTER_NAME = stringPreferencesKey("user_filter_name")
        val IS_CHECKED_COMPANY = booleanPreferencesKey("is_checked_company")
        val IS_CHECKED_AGENCY = booleanPreferencesKey("is_checked_agency")
        val IS_CHECKED_DIRECTOR = booleanPreferencesKey("is_checked_director")
        val IS_CHECKED_RECRUITER = booleanPreferencesKey("is_checked_recruiter")
    }

    val getName: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_FILTER_NAME]
        }
    val getInfo: Flow<Boolean?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_FILTER_KEY]
        }
    val getType: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_FILTER_TYPE]
        }
    val getCheckCompany: Flow<Boolean?> = context.dataStore.data
        .map { prefs ->
            prefs[IS_CHECKED_COMPANY]
        }
    val getCheckAgency: Flow<Boolean?> = context.dataStore.data
        .map { prefs ->
            prefs[IS_CHECKED_AGENCY]
        }
    val getCheckDirector: Flow<Boolean?> = context.dataStore.data
        .map { prefs ->
            prefs[IS_CHECKED_DIRECTOR]
        }
    val getCheckRecruiter: Flow<Boolean?> = context.dataStore.data
        .map { prefs ->
            prefs[IS_CHECKED_RECRUITER]
        }

    suspend fun isCheckedCompany(isChecked: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[IS_CHECKED_COMPANY] = isChecked
        }
    }

    suspend fun isCheckedAgency(isChecked: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[IS_CHECKED_AGENCY] = isChecked
        }
    }

    suspend fun isCheckedDirector(isChecked: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[IS_CHECKED_DIRECTOR] = isChecked
        }
    }

    suspend fun isCheckedRecruiter(isChecked: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[IS_CHECKED_RECRUITER] = isChecked
        }
    }

    suspend fun saveInfo(isVacancies: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[USER_FILTER_KEY] = isVacancies
        }
    }

    suspend fun saveType(type: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_FILTER_TYPE] = type
        }
    }

    suspend fun saveName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_FILTER_NAME] = name
        }
    }

}