import extension.addDataModuleDependencies
import extension.addInstrumentationTestDependencies
import extension.addUnitTestDependencies

plugins {
    id(Plugins.ANDROID_LIBRARY_PLUGIN)
    id(Plugins.KOTLIN_ANDROID_PLUGIN)
    id(Plugins.KOTLIN_KAPT_PLUGIN)
    id(Plugins.KOTLIN_PARCELIZE_PLUGIN)
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

        // TODO Scheme is created in data module but with which one, find out
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

    implementation(project(Modules.AndroidLibrary.COMMON))
    implementation(project(Modules.AndroidLibrary.DOMAIN))

    addDataModuleDependencies()

    addUnitTestDependencies()

    addInstrumentationTestDependencies()
}
