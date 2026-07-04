package com.xeditor.app.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.xeditor.app.domain.ThemeMode
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.settingsDataStore by preferencesDataStore(name = "xeditor_settings")

/**
 * Persists theme-related settings (and, over time, other app-wide
 * preferences) to disk via DataStore, so choices made on the Settings
 * screen survive app restarts.
 */
@Singleton
class SettingsRepository @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private object Keys {
        val THEME_MODE = stringPreferencesKey("theme_mode")
        val DYNAMIC_COLOR_ENABLED = booleanPreferencesKey("dynamic_color_enabled")
    }

    val themeMode: Flow<ThemeMode> = context.settingsDataStore.data.map { preferences ->
        preferences[Keys.THEME_MODE]?.let { storedValue ->
            runCatching { ThemeMode.valueOf(storedValue) }.getOrNull()
        } ?: ThemeMode.SYSTEM
    }

    val dynamicColorEnabled: Flow<Boolean> = context.settingsDataStore.data.map { preferences ->
        preferences[Keys.DYNAMIC_COLOR_ENABLED] ?: true
    }

    suspend fun setThemeMode(mode: ThemeMode) {
        context.settingsDataStore.edit { preferences ->
            preferences[Keys.THEME_MODE] = mode.name
        }
    }

    suspend fun setDynamicColorEnabled(enabled: Boolean) {
        context.settingsDataStore.edit { preferences ->
            preferences[Keys.DYNAMIC_COLOR_ENABLED] = enabled
        }
    }
}
