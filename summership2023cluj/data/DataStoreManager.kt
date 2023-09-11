package com.example.summership2023cluj.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.summership2023cluj.data.dto.ProfileData
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "datastore")

class DataStoreManager(var context: Context) {
    private val TOKEN_KEY = stringPreferencesKey("token")
    private val PROFILE_KEY = stringPreferencesKey("profile")
    private fun getDataStore(context: Context): DataStore<Preferences> {
        return context.dataStore
    }

    suspend fun saveToken(token: String) {
        getDataStore(context).edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    suspend fun saveProfile(profile: ProfileData) {
        getDataStore(context).edit { preferences ->
            val gson = Gson()
            val profileDataString = gson.toJson(profile)
            preferences[PROFILE_KEY] = profileDataString
        }
    }

    suspend fun getToken(): String? {

        val token = getDataStore(context).data.map { preferences ->
            preferences[TOKEN_KEY]
        }.first()
        return token
    }

    suspend fun getProfile(): ProfileData {
        return getDataStore(context).data.map { preferences ->
            val gson = Gson()
            val profile: ProfileData =
                gson.fromJson(preferences[PROFILE_KEY], ProfileData::class.java)
            profile
        }.first()
    }
}