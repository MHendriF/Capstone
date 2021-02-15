// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Plugins.CLASSPATH_GRADLE)
        classpath(Plugins.CLASSPATH_KOTLIN)
        classpath(Plugins.CLASSPATH_KTLINT)
        classpath(Plugins.CLASSPATH_NAV_SAFE_ARGS)
        classpath(Plugins.CLASSPATH_GOOGLE_SERVICE)
        classpath(Plugins.CLASSPATH_FIREBASE_CRASHLYTICS)
        classpath(Plugins.CLASSPATH_DAGGER_HILT)
    }
}

plugins {
    id(Plugins.KTLINT) version PluginVersion.KTLINT_VERSION
    id(Plugins.DETEKT) version PluginVersion.DETEKT_VERSION
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

subprojects {

    tasks.withType<Test> {
        maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
    }

    // KtLint
    apply(plugin = Plugins.KTLINT) // Version should be inherited from parent

    // KtLint Configurations
    ktlint {
        debug.set(true)
        verbose.set(true)
        android.set(true)
        outputToConsole.set(true)
    }

    // Detekt
    apply(plugin = Plugins.DETEKT)

    detekt {
        config = files("${project.rootDir}/config/detekt/detekt.yml")
        parallel = true

        reports {
            xml {
                enabled = true
                destination = file("${project.rootDir}/build/reports/detekt_report.xml")
            }
            html {
                enabled = true
                destination = file("${project.rootDir}/build/reports/detekt_report.html")
            }
            txt {
                enabled = true
                destination = file("${project.rootDir}/build/reports/detekt_report.txt")
            }
        }
    }
}

// JVM target applied to all Kotlin tasks across all sub-projects
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}

task("clean") {
    delete(rootProject.buildDir)
}
