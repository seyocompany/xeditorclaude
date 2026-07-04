package com.xeditor.app.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xeditor.app.data.SettingsRepository
import com.xeditor.app.domain.ThemeMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SettingsUiState(
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val dynamicColorEnabled: Boolean = true,
)

/**
 * Backs both the Settings screen and MainActivity's theme decision.
 *
 * Each screen gets its own instance of this ViewModel (Hilt scopes them
 * to wherever they're requested), but both instances read from the same
 * underlying DataStore-backed repository, so a change made on the
 * Settings screen is reflected in MainActivity's theme immediately —
 * no manual syncing required.
 */
@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: SettingsRepository,
) : ViewModel() {

    val uiState: StateFlow<SettingsUiState> = combine(
        repository.themeMode,
        repository.dynamicColorEnabled,
    ) { themeMode, dynamicColorEnabled ->
        SettingsUiState(themeMode = themeMode, dynamicColorEnabled = dynamicColorEnabled)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue = SettingsUiState(),
    )

    fun setThemeMode(mode: ThemeMode) {
        viewModelScope.launch { repository.setThemeMode(mode) }
    }

    fun setDynamicColorEnabled(enabled: Boolean) {
        viewModelScope.launch { repository.setDynamicColorEnabled(enabled) }
    }
}
