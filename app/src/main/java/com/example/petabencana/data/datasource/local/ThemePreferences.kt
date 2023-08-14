package com.example.petabencana.data.datasource.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.prefDataStore by preferencesDataStore("settings")
class ThemePreferences constructor(context:Context) {

    private val settingPreferences = context.prefDataStore
    private val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")

    fun getIsDarkMode(): Flow<Boolean> {
        return settingPreferences.data.map {
            preference-> preference[IS_DARK_MODE] ?: false
        }
    }

    suspend fun setIsDarkMode(value: Boolean){
        settingPreferences.edit {
            preference-> preference[IS_DARK_MODE] = value
        }
    }
}