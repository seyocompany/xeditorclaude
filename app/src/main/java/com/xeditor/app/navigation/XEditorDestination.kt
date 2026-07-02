package com.xeditor.app.navigation

/**
 * Every top-level screen the app can navigate to.
 *
 * Using a sealed class instead of raw route strings means the compiler
 * catches typos and missing destinations at build time instead of at
 * runtime. Screens themselves (Home, Settings, About) get built out in
 * later roadmap steps — this step only wires up the navigation skeleton
 * they'll plug into.
 */
sealed class XEditorDestination(val route: String) {
    data object Home : XEditorDestination(route = "home")
    data object Settings : XEditorDestination(route = "settings")
    data object About : XEditorDestination(route = "about")
}
