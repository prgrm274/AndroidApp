plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs") //# Agar bisa menggunakan FragmentDirections.action.. saat misal klik button menuju fragment lain
    id("com.google.gms.google-services") //firebase
//    id("com.google.devtools.ksp") version "1.9.10-1.0.13"
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
    id("kotlinx-serialization")
}

android {
    namespace = "com.programmer270487.dansandroid"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.programmer270487.dansandroid"
        minSdk = 21
        targetSdk = 34//33//32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "com.HiltTestRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
        multiDexEnabled = true
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }

    testOptions {
        unitTests {
            all {
            }
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    hilt {
        // use this if error: The following options were not recognized by any processor: dagger.hilt.internal.useAggregatingRootProcessor
        enableAggregatingTask = true
    }
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.material3:material3")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    val kotlin = "1.9.0"
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin")
    implementation("androidx.activity:activity:$kotlin")
    implementation("androidx.activity:activity-ktx:$kotlin")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    val nav = "2.7.7"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav")
    implementation("androidx.navigation:navigation-ui-ktx:$nav")
    val lifecycle = "2.7.0"
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle")
    implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycle")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0") // 1.6.1
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    implementation("androidx.activity:activity-compose:$kotlin")
    val composeP = "1.4.0"
    implementation("androidx.compose.ui:ui:$composeP")
    implementation("androidx.compose.material:material:$composeP")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeP")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeP")

    val kotlinStdLibJdk8 = "1.8.22"
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinStdLibJdk8")
    val korotin = "1.7.3"
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$korotin")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:$korotin")
    implementation("androidx.preference:preference-ktx:1.1.1")

    val work = "2.9.0"
    implementation("androidx.work:work-runtime-ktx:$work")
    implementation("androidx.work:work-rxjava2:$work")
    implementation("androidx.work:work-multiprocess:$work")

    //! ReactiveX
    implementation("io.reactivex.rxjava2:rxjava:2.2.21")
    implementation("io.reactivex.rxjava2:rxkotlin:2.4.0")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

    val hilt = "2.48"
    implementation("com.google.dagger:hilt-android:$hilt")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    ksp("com.google.dagger:hilt-compiler:$hilt")
    ksp("com.google.dagger:hilt-android-compiler:$hilt")
    ksp("androidx.hilt:hilt-compiler:1.0.0")

    val multidex = "2.0.1"
    implementation("androidx.multidex:multidex:$multidex")

    val room = "2.6.1"
    implementation("androidx.room:room-runtime:$room")
    implementation("androidx.room:room-ktx:$room")
    implementation("androidx.room:room-paging:$room")
    ksp("androidx.room:room-compiler:$room")

    val paging = "3.2.1"
    val pagingP = "1.0.0-alpha18"
    implementation("androidx.paging:paging-compose:$pagingP")
    implementation("androidx.paging:paging-rxjava2-ktx:$paging")
    implementation("androidx.paging:paging-runtime-ktx:$paging")

    implementation("androidx.datastore:datastore-preferences:1.1.0")

    val retrofit = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit")
    implementation("com.squareup.retrofit2:adapter-rxjava2:$retrofit")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    val okhttp = "4.10.0"
    implementation("com.squareup.okhttp3:okhttp:$okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttp")
    implementation("com.squareup.okhttp3:okhttp-urlconnection:$okhttp")

    implementation("io.coil-kt:coil-compose:2.2.2")
}