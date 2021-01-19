object CommonDeps {

    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib:${PluginVersion.KOTLIN_VERSION}"

    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${Version.CORE_KTX_VERSION}"

    // AppCompat
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Version.APPCOMPAT_VERSION}"

    // Material
    const val MATERIAL = "com.google.android.material:material:${Version.MATERIAL_VERSION}"

    // ConstraintLayout
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Version.CONSTRAINT_LAYOUT_VERSION}"

    // Lifecycle, ViewModel and LiveData
    const val LIFECYCLE_LIVEDATA_KTX =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Version.LIFECYCLE_VERSION}"
    const val LIFECYCLE_VIEWMODEL_KTX =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.LIFECYCLE_VERSION}"
    const val LIFECYCLE_EXTENSIONS =
        "androidx.lifecycle:lifecycle-extensions:${Version.LIFECYCLE_VERSION}"

    // Navigation Components
    const val NAVIGATION_FRAGMENT =
        "androidx.navigation:navigation-fragment-ktx:${Version.NAVIGATION_VERSION}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Version.NAVIGATION_VERSION}"
    const val NAVIGATION_RUNTIME =
        "androidx.navigation:navigation-runtime-ktx:${Version.NAVIGATION_VERSION}"

    // Dynamic Feature Module Support
    const val NAVIGATION_DYNAMIC =
        "androidx.navigation:navigation-dynamic-features-fragment:${Version.NAVIGATION_VERSION}"

    // Dynamic Feature Module Support
    const val LEGACY =
        "androidx.legacy:legacy-support-v4:${Version.ANDROIDX_LEGACY_VERSION}"

    // Dagger Core dependencies
    const val DAGGER = "com.google.dagger:dagger:${Version.DAGGER_VERSION}"
    const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:${Version.DAGGER_VERSION}"
    const val DAGGER_ANNOTATION_PROCESSOR = "com.google.dagger:dagger-android-processor:${Version.DAGGER_VERSION}"

    // Coroutines
    const val COROUTINES_CORE =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.COROUTINES_VERSION}"
    const val COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.COROUTINES_VERSION}"

    // Lottie
    const val LOTTIE = "com.airbnb.android:lottie:${Version.LOTTIE_VERSION}"

    // Leak Canary
    const val LEAK_CANARY =
        "com.squareup.leakcanary:leakcanary-android:${Version.LEAK_CANARY_VERSION}"

    // SSP & SDP
    const val SSP_ANDROID = "com.intuit.ssp:ssp-android:${Version.SSP_ANDROID_VERSION}"
    const val SDP_ANDROID = "com.intuit.sdp:sdp-android:${Version.SDP_ANDROID_VERSION}"

    // Rounded Image View
    const val ROUNDED_IMAGE = "com.makeramen:roundedimageview:${Version.ROUNDED_IMAGE_VERSION}"

    // Picasso
    const val PICASSO = "com.squareup.picasso:picasso:${Version.PICASSO_VERSION}"

    // Multidex
    const val MULTIDEX = "androidx.multidex:multidex:${Version.MULTIDEX_VERSION}"

    // Paging
    const val PAGING  = "androidx.paging:paging-runtime-ktx:${Version.PAGING_VERSION}"
}

object DebugDeps {
    // Timber
    const val TIMBER = "com.jakewharton.timber:timber:${Version.TIMBER_VERSION}"

    // Firebase
    const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${Version.FIREBASE_BOM}"
    const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics-ktx"
    const val FIREBASE_CRASHLYTICS = "com.google.firebase:firebase-crashlytics-ktx"
}

object NetworkDeps {
    // Retrofit
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Version.RETROFIT_VERSION}"
    const val RETROFIT_GSON_CONVERTER = "com.squareup.retrofit2:converter-gson:${Version.RETROFIT_VERSION}"

    // Okhttp
    const val OK_HTTP3 = "com.squareup.okhttp3:okhttp:${Version.OK_HTTP3_VERSION}"
    const val OK_HTTP3_LOG = "com.squareup.okhttp3:logging-interceptor:${Version.OK_HTTP3_VERSION}"
}

object DataDeps {
    // Room
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Version.ROOM_VERSION}"
    // For Kotlin use kapt instead of annotationProcessor
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Version.ROOM_VERSION}"
    // optional - Kotlin Extensions and Coroutines support for Room
    const val ROOM_KTX = "androidx.room:room-ktx:${Version.ROOM_VERSION}"

    const val SQLCIPHER = "net.zetetic:android-database-sqlcipher:${Version.SQLCIPHER_VERSION}"

    const val SQLITE = "androidx.sqlite:sqlite-ktx:${Version.SQLITE_VERSION}"
}

object TestDeps {

    // (Required) Writing and executing Unit Tests on the JUnit Platform
    const val JUNIT_DEFAULT = "junit:junit:${TestVersion.junit4Version}"

    const val ANDROIDX_CORE_TESTING = "androidx.arch.core:core-testing:${TestVersion.archTestingVersion}"

    // AndroidX Test - JVM testing
    const val ANDROIDX_JUNIT =
        "androidx.test.ext:junit:${TestVersion.androidXTestExtKotlinRunnerVersion}"

    // Espresso
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${TestVersion.espressoVersion}"
    const val ESPRESSO_CONTRIB = "androidx.test.espresso:espresso-contrib:${TestVersion.espressoVersion}"
    const val ESPRESSO_IDLING_RESOURCE = "androidx.test.espresso:espresso-idling-resource:${TestVersion.espressoVersion}"

    // Testing Navigation
    const val NAVIGATION_TEST = "androidx.navigation:navigation-testing:${Version.NAVIGATION_VERSION}"
}

