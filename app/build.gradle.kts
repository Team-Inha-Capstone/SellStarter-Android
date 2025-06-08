plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.21"
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.inha.sellstarter_android"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.inha.sellstarter_android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        ndk {
            abiFilters += listOf("armeabi-v7a", "arm64-v8a")
        }

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
        allWarningsAsErrors = false
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.viewmodel.compose)
    implementation(libs.androidx.navigation.navigation.compose)

    // coroutine, okhttp, retrofit, serialization
    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.okhttp)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.serialization)

    // hilt (Dependency Injection)
    implementation(libs.dagger.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kapt("com.google.dagger:hilt-android-compiler:2.46.1")

    // paging
    implementation(libs.bundles.paging)
    testImplementation(libs.paging.common)

    implementation(libs.androidx.compose.material.meterial)
    implementation(libs.compose.foundation)

    // coil
    implementation(libs.coil.compose)

    //zxing
    implementation(libs.bundles.zxing)

    //DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // moshi
    implementation(libs.bundles.moshi)

    // vico
    implementation(libs.bundles.vico)

    //lottie
    implementation(libs.lottie)


    // implementation("com.google.android.filament:filament-android:1.33.1")
    // implementation("com.google.android.filament:gltfio-android:1.33.1")
}