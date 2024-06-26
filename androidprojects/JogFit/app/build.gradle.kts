plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.gms.google.services)
    id("com.google.dagger.hilt.android") version "2.51.1"
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("com.ncorti.ktfmt.gradle") version "0.10.0"
    id("com.google.firebase.crashlytics")

}

android {
    namespace = "android.caged.jogfit"
    compileSdk = 34

    defaultConfig {
        applicationId = "android.caged.jogfit"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.lifecycle.service)
    implementation(libs.androidx.runtime.livedata)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Splash Api
    implementation(libs.androidx.core.splashscreen)

    //Compose Navigation
//    implementation(libs.androidx.navigation.compose.v253)

//    //Dagger Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)


//    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
//
//    //Coil
    implementation(libs.coil.compose)

//    //Datastore
    implementation(libs.androidx.datastore.preferences)
//
//    //Compose Foundation
    implementation(libs.androidx.foundation)
//
//    //Accompanist
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.accompanist.permissions)

//    //Room
////    def room_version = "2.5.2"
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    implementation(libs.play.services.maps)
    implementation(libs.android.maps.utils)
// Maps SDK for Android KTX Library
    implementation(libs.maps.ktx)
    implementation(libs.maps.compose)


    // Maps SDK for Android Utility Library KTX Library
    implementation(libs.maps.utils.ktx)


    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.perf)
    implementation(libs.firebase.config)
    implementation(libs.firebase.crashlytics)

    // Credential Manager
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
    implementation(libs.kotlinx.coroutines.play.services.v164)

    implementation(libs.androidx.preference.ktx)
    implementation(libs.kotlinx.coroutines.play.services)
    implementation(libs.accompanist.permissions)

    implementation(libs.androidx.datastore.preferences)

    // Dependency to include Maps SDK for Android
    implementation("com.google.android.gms:play-services-maps:18.0.2")
    implementation("com.google.maps.android:android-maps-utils:1.1.0")
// Maps SDK for Android KTX Library
    implementation("com.google.maps.android:maps-ktx:3.0.0")

    // Maps SDK for Android Utility Library KTX Library
    implementation("com.google.maps.android:maps-utils-ktx:3.0.0")
    implementation("com.google.android.gms:play-services-location:21.3.0")
}