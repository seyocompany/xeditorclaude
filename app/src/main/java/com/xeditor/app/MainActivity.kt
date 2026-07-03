package com.xeditor.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.xeditor.app.navigation.XEditorNavHost
import com.xeditor.app.ui.theme.XEditorTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Single-activity host for the whole app.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            XEditorTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    XEditorNavHost()
                }
            }
        }
    }
}
