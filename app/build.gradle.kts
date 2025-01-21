plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.eeerrorcode.cookietodo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.eeerrorcode.cookietodo"
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

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // https://mvnrepository.com/artifact/androidx.room/room-common
    implementation("androidx.room:room-common:2.6.1")

    // https://mvnrepository.com/artifact/androidx.room/room-runtime
    implementation("androidx.room:room-runtime:2.6.1")

    // https://mvnrepository.com/artifact/androidx.room/room-ktx
    implementation("androidx.room:room-ktx:2.6.1")

    // https://mvnrepository.com/artifact/androidx.room/room-compiler
    implementation("androidx.room:room-compiler:2.6.1")
}