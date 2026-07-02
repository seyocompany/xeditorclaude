// Top-level build file. Module-specific config lives in app/build.gradle.kts.
plugins {
    // AGP 9.0+ compiles Kotlin natively, so the separate
    // org.jetbrains.kotlin.android plugin is no longer applied here.
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose.compiler) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.ksp) apply false
}
