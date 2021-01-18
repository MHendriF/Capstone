object Plugins {

    /*
       Project Level
    */
    const val DETEKT = "io.gitlab.arturbosch.detekt"
    const val KTLINT = "org.jlleitschuh.gradle.ktlint"

    const val CLASSPATH_GRADLE = "com.android.tools.build:gradle:${PluginVersion.GRADLE_VERSION}"
    const val CLASSPATH_KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginVersion.KOTLIN_VERSION}"
    const val CLASSPATH_KTLINT = "org.jlleitschuh.gradle:ktlint-gradle:${PluginVersion.KTLINT_VERSION}"
    const val CLASSPATH_NAV_SAFE_ARGS = "androidx.navigation:navigation-safe-args-gradle-plugin:${PluginVersion.NAV_SAFE_ARGS_VERSION}"
    const val CLASSPATH_GOOGLE_SERVICE = "com.google.gms:google-services:${PluginVersion.GOOGLE_GMS_VERSION}"
    const val CLASSPATH_FIREBASE_CRASHLYTICS = "com.google.firebase:firebase-crashlytics-gradle:${PluginVersion.FIREBASE_CRASHLYTICS_VERSION}"
    const val CLASSPATH_LEAKCANARY = "com.squareup.leakcanary:leakcanary-deobfuscation-gradle-plugin:${PluginVersion.LEAKCANARY_VERSION}"

    /*
        Module Level
     */
    const val ANDROID_APPLICATION_PLUGIN = "com.android.application"
    const val ANDROID_DYNAMIC_FEATURE_PLUGIN = "com.android.dynamic-feature"
    const val ANDROID_LIBRARY_PLUGIN = "com.android.library"

    const val KOTLIN_ANDROID_PLUGIN = "kotlin-android"
    const val KOTLIN_KAPT_PLUGIN = "kotlin-kapt"
    const val KOTLIN_PARCELIZE_PLUGIN = "kotlin-parcelize"
    const val NAVIGATION_PLUGIN = "androidx.navigation.safeargs.kotlin"

    const val GOOGLE_SERVICE_PLUGIN = "com.google.gms.google-services"
    const val FIREBASE_CRASHLYTICS_PLUGIN = "com.google.firebase.crashlytics"
    const val LEAKCANARY_DEOBFUSCATION_PLUGIN = "com.squareup.leakcanary.deobfuscation"
}
