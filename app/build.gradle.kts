plugins {
    id("com.android.application")
}

android {
    namespace = "com.myproyect.HistorialMedico"
    compileSdk = 33

    configurations.all {
        resolutionStrategy.eachDependency {
            if (requested.group == "org.jetbrains.kotlin") {
                useVersion("1.8.10")
                because("Force consistent Kotlin version to avoid duplicate classes")
            }
        }
    }

    defaultConfig {
        applicationId = "com.myproyect.HistorialMedico"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }


}

dependencies {
    implementation("androidx.core:core:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1") {
        exclude(group = "org.jetbrains.kotlin")
    }
    implementation("com.google.android.material:material:1.9.0") {
        exclude(group = "org.jetbrains.kotlin")
    }
    implementation("androidx.constraintlayout:constraintlayout:2.1.4") {
        exclude(group = "org.jetbrains.kotlin")
    }
    implementation("androidx.room:room-runtime:2.5.1")
    annotationProcessor("androidx.room:room-compiler:2.5.1")
    implementation("androidx.lifecycle:lifecycle-runtime:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.6.1")
    implementation("androidx.security:security-crypto:1.1.0-alpha03") // Para encriptación de datos
    implementation ("androidx.cardview:cardview:1.0.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
