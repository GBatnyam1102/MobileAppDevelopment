package com.example.flashcardapp.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

class SettingsDataStore(private val context: Context) {

    private val SELECTED_OPTION_KEY = stringPreferencesKey("selected_option")

    val selectedOptionFlow: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[SELECTED_OPTION_KEY] ?: "Гадаад үгийг ил харуулах"
        }

    suspend fun saveSelectedOption(option: String) {
        context.dataStore.edit { preferences ->
            preferences[SELECTED_OPTION_KEY] = option
        }
    }
}
