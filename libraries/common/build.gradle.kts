import extension.addCommonModuleDependencies
import extension.addInstrumentationTestDependencies
import extension.addUnitTestDependencies
import java.util.Properties

plugins {
    id(Plugins.ANDROID_LIBRARY_PLUGIN)
    id(Plugins.KOTLIN_ANDROID_PLUGIN)
    id(Plugins.KOTLIN_KAPT_PLUGIN)
}

val localProperties = Properties()
localProperties.load(project.rootProject.file("local.properties").inputStream())

android {
    compileSdkVersion(AndroidVersion.COMPILE_SDK_VERSION)
    buildToolsVersion(AndroidVersion.BUILD_TOOLS_VERSION)
    defaultConfig {
        minSdkVersion(AndroidVersion.MIN_SDK_VERSION)
        targetSdkVersion(AndroidVersion.TARGET_SDK_VERSION)
        versionCode(AndroidVersion.VERSION_CODE)
        versionName = AndroidVersion.VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "TMDB_API_KEY", localProperties["API_KEY"] as String)
        buildConfigField("String", "DB_PASSPHRASE", localProperties["PASSPHRASE"] as String)
    }

    android.buildFeatures.dataBinding = true
    android.lintOptions.isAbortOnError = false

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    addCommonModuleDependencies()

    addUnitTestDependencies()

    addInstrumentationTestDependencies()
}
