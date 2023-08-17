
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.com.google.android.libraries.mapsplatform.secrets.gradle.plugin)
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.example.petabencana"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.petabencana"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


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
        viewBinding = true
        buildConfig = true
    }

}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.places)
    implementation(libs.legacy.support.v4)
    implementation(libs.androidx.baseLibrary)

    /// viemodel delegation ktx
    implementation(libs.androidx.activity)
    implementation(libs.androidx.fragment)
    implementation(libs.lifecycle.viewmodel.ktx)

    /// Navigation component
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.fragment)

    /// retrofit network handler
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.coil)

    ///live data lifecycle
    implementation(libs.livedata)
    implementation(libs.lifecycle.livedata.ktx)

    /// Image Loaded
    implementation(libs.glide)

    /// shared preference
    implementation(libs.androidx.datastore.preferences)

    /// depedency injection
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    ///Maps
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

}
