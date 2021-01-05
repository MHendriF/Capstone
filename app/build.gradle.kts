import extension.addAppModuleDependencies
import extension.addInstrumentationTestDependencies
import extension.addUnitTestDependencies

plugins {
    id(Plugins.ANDROID_APPLICATION_PLUGIN)
    id(Plugins.KOTLIN_ANDROID_PLUGIN)
    id(Plugins.KOTLIN_KAPT_PLUGIN)
    id(Plugins.KOTLIN_PARCELIZE_PLUGIN)
    id(Plugins.DAGGER_HILT_PLUGIN)
    id(Plugins.NAVIGATION_PLUGIN)
}

android {
    compileSdkVersion(AndroidVersion.COMPILE_SDK_VERSION)
    buildToolsVersion(AndroidVersion.BUILD_TOOLS_VERSION)
    defaultConfig {
        minSdkVersion(AndroidVersion.MIN_SDK_VERSION)
        targetSdkVersion(AndroidVersion.TARGET_SDK_VERSION)
        versionCode(AndroidVersion.VERSION_CODE)
        versionName = AndroidVersion.VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // TODO Scheme is  created in data module but with which one, find out
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }
        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }

    android.buildFeatures.dataBinding = true
    android.buildFeatures.viewBinding = true

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
    implementation(project(Modules.AndroidLibrary.CORE))

    addAppModuleDependencies()

    addUnitTestDependencies()

    addInstrumentationTestDependencies()
}