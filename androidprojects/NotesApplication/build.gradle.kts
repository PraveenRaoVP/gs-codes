// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.gms.google.services) apply false
    id("com.google.devtools.ksp") version "1.9.23-1.0.20" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.21" apply false
    id("com.google.dagger.hilt.android") version "2.47" apply false
    id("com.google.firebase.firebase-perf") version "1.4.2" apply false
    id("com.google.firebase.crashlytics") version "3.0.1" apply false

}