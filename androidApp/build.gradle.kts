plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.rickandmortykmm.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.rickandmortykmm.android"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(Dependencies.composeUI)
    implementation(Dependencies.composeTooling)
    implementation(Dependencies.composePreview)
    implementation(Dependencies.composeFoundation)
    implementation(Dependencies.composeActivity)
    implementation(Dependencies.composeNavigation)
    implementation(Dependencies.composeUtil)
    implementation(Dependencies.material3)
    implementation(Dependencies.koinAndroid)
    implementation(Dependencies.coil)
    implementation(Dependencies.window)
}