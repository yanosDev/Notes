buildscript {
    repositories {
        google()
        mavenCentral()

        if (!libs.versions.compose.snapshot.get().endsWith("SNAPSHOT")) {
            maven { url = uri("https://androidx.dev/snapshots/builds/${libs.versions.compose.snapshot.get()}/artifacts/repository/") }
        }
    }
    dependencies {
        classpath(libs.android.gradlePlugin)
        classpath(libs.kotlin.gradlePlugin)
        classpath(libs.google.services)
    }
}

subprojects {
    repositories {
        google()
        mavenCentral()
    }
}
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kapt).apply(false)
    alias(libs.plugins.hilt).apply(false)
    alias(libs.plugins.benManes).apply(true)
    alias(libs.plugins.versionCatalog).apply(true)
}

apply("${project.rootDir}/buildscripts/toml-updater-config.gradle")
