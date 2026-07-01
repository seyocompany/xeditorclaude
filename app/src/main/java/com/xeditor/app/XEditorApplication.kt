package com.xeditor.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application entry point.
 *
 * Annotated with @HiltAndroidApp so Hilt generates the app-level
 * dependency container that every other Hilt-aware component (Activities,
 * ViewModels, WorkManager Workers, etc. added in later roadmap steps)
 * attaches to.
 */
@HiltAndroidApp
class XEditorApplication : Application()
