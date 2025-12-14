plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.services) apply false
    id("com.github.ben-manes.versions") version "0.48.0"
}



    android {
        namespace = "com.example.risklocator"
        compileSdk = 35

        defaultConfig {
            applicationId = "com.example.risklocator"
            minSdk = 24
            targetSdk = 35
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
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
        kotlinOptions {
            jvmTarget = "11"
        }
    }

buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.3.15")
    }
}


   dependencies {
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.appcompat)
        implementation(libs.material)
       implementation(platform("com.google.firebase:firebase-bom:32.7.2"))
       implementation("com.google.firebase:firebase-analytics")
       testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
       implementation("com.google.firebase:firebase-auth-ktx")
       implementation("com.google.firebase:firebase-firestore-ktx")
       implementation("com.google.firebase:firebase-messaging-ktx")
   }
