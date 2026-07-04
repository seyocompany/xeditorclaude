package com.xeditor.app.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xeditor.app.ui.home.HomeScreen
import com.xeditor.app.ui.settings.SettingsScreen

/**
 * Root navigation graph for the app.
 *
 * Home and Settings are fully built out now. About stays a placeholder
 * until its own roadmap step replaces it.
 */
@Composable
fun XEditorNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = XEditorDestination.Home.route,
    ) {
        composable(route = XEditorDestination.Home.route) {
            HomeScreen(
                onNavigateToSettings = { navController.navigate(XEditorDestination.Settings.route) },
                onNavigateToAbout = { navController.navigate(XEditorDestination.About.route) },
            )
        }
        composable(route = XEditorDestination.Settings.route) {
            SettingsScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable(route = XEditorDestination.About.route) {
            PlaceholderScreen(label = "About")
        }
    }
}

@Composable
private fun PlaceholderScreen(label: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "$label — coming in a later step")
    }
}
