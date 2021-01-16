object Plugins {

    /*
       Project Level
    */
    const val DETEKT = "io.gitlab.arturbosch.detekt"
    const val KTLINT = "org.jlleitschuh.gradle.ktlint"

    const val CLASSPATH_GRADLE = "com.android.tools.build:gradle:${PluginVersion.GRADLE_VERSION}"
    const val CLASSPATH_KOTLIN_GRADLE = "org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginVersion.KOTLIN_VERSION}"
    const val CLASSPATH_KTLINT = "org.jlleitschuh.gradle:ktlint-gradle:${PluginVersion.KTLINT_VERSION}"
    const val CLASSPATH_NAV_SAFE_ARGS = "androidx.navigation:navigation-safe-args-gradle-plugin:${PluginVersion.NAV_SAFE_ARGS_VERSION}"

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
}
