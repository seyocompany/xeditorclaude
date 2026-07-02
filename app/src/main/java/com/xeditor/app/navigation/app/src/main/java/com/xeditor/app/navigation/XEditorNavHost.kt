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

/**
 * Root navigation graph for the app.
 *
 * Each destination below is a temporary placeholder screen — just enough
 * to prove navigation works end to end. The real Home screen, Settings
 * screen, and About screen get built out in their own roadmap steps and
 * will replace these placeholders one at a time without touching this
 * graph's structure.
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
            PlaceholderScreen(label = "Home")
        }
        composable(route = XEditorDestination.Settings.route) {
            PlaceholderScreen(label = "Settings")
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
