package com.xeditor.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.xeditor.app.domain.ThemeMode
import com.xeditor.app.navigation.XEditorNavHost
import com.xeditor.app.settings.SettingsViewModel
import com.xeditor.app.ui.theme.XEditorTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Single-activity host for the whole app.
 *
 * Theme mode and dynamic-color preference come from SettingsViewModel
 * (backed by DataStore — see Settings, Phase 1 Step 5), so a choice made
 * on the Settings screen is reflected here immediately and persists
 * across app restarts.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val settingsViewModel: SettingsViewModel = hiltViewModel()
            val uiState by settingsViewModel.uiState.collectAsState()
            val systemInDarkTheme = isSystemInDarkTheme()

            val darkTheme = when (uiState.themeMode) {
                ThemeMode.SYSTEM -> systemInDarkTheme
                ThemeMode.LIGHT -> false
                ThemeMode.DARK -> true
            }

            XEditorTheme(
                darkTheme = darkTheme,
                dynamicColor = uiState.dynamicColorEnabled,
            ) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    XEditorNavHost()
                }
            }
        }
    }
}
