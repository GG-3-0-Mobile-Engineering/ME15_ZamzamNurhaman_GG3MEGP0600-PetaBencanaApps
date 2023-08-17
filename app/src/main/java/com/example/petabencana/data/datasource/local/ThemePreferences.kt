package com.example.petabencana.data.datasource.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ThemePreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    private val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")

    fun getIsDarkMode(): Flow<Boolean> {
        return dataStore.data.map {
            preference-> preference[IS_DARK_MODE] ?: false
        }
    }

    suspend fun setIsDarkMode(value: Boolean){
        dataStore.edit {
            preference-> preference[IS_DARK_MODE] = value
        }
    }
}