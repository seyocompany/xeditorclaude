package com.xeditor.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.xeditor.app.navigation.XEditorNavHost
import dagger.hilt.android.AndroidEntryPoint

/**
 * Single-activity host for the whole app.
 *
 * Real theming (Material 3 + dynamic color + dark mode) is built in the
 * next roadmap step. For now this just wraps the navigation graph in a
 * plain MaterialTheme so the app has readable colors.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    XEditorNavHost()
                }
            }
        }
    }
}
