// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
}
buildscript {

    repositories {
        google()
    }
    dependencies {

//        classpath("com.android.tools.build:gradle:7.1.0")
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
//        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }

}


