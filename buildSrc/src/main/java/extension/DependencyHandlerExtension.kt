package extension

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * Adds required dependencies to app module
 */
fun DependencyHandler.addAppModuleDependencies() {
    // Dagger2
    implementation(CommonDeps.DAGGER)
    kapt(CommonDeps.DAGGER_COMPILER)
    kapt(CommonDeps.DAGGER_ANNOTATION_PROCESSOR)

    // Navigation Components
    api(CommonDeps.NAVIGATION_FRAGMENT)
    api(CommonDeps.NAVIGATION_UI)
    api(CommonDeps.NAVIGATION_RUNTIME)
    api(CommonDeps.NAVIGATION_DYNAMIC)

    // Lifecycle, LiveData, ViewModel
    api(CommonDeps.LIFECYCLE_LIVEDATA_KTX)
    api(CommonDeps.LIFECYCLE_VIEWMODEL_KTX)
    api(CommonDeps.LIFECYCLE_EXTENSIONS)

    // Leak Canary
    debugImplementation(CommonDeps.LEAK_CANARY)

    // Rounded Image
    implementation(CommonDeps.ROUNDED_IMAGE)

    // Paging
    implementation(CommonDeps.PAGING)

    implementation(CommonDeps.MULTIDEX)

    // Tools Debuging
    api(platform(DebugDeps.FIREBASE_BOM))
    api(DebugDeps.FIREBASE_ANALYTICS)
}

/**
 * Adds dependencies to common module
 */
fun DependencyHandler.addCommonModuleDependencies() {
    api(CommonDeps.KOTLIN)
    api(CommonDeps.ANDROIDX_CORE_KTX)

    // Support and Widgets
    api(CommonDeps.APPCOMPAT)
    api(CommonDeps.MATERIAL)
    api(CommonDeps.CONSTRAINT_LAYOUT)
    api(CommonDeps.LEGACY)

    // Views, Animations
    api(CommonDeps.LOTTIE)
    api(CommonDeps.SSP_ANDROID)
    api(CommonDeps.SDP_ANDROID)

    // Tools Debuging
    api(DebugDeps.TIMBER)
}

/**
 * Adds dependencies to domain module
 */
fun DependencyHandler.addDomainModuleDependencies() {
    // Dagger2
    implementation(CommonDeps.DAGGER)
    kapt(CommonDeps.DAGGER_COMPILER)
    kapt(CommonDeps.DAGGER_ANNOTATION_PROCESSOR)

    // Coroutines
    api(CommonDeps.COROUTINES_CORE)
    api(CommonDeps.COROUTINES_ANDROID)

    // Retrofit
    api(NetworkDeps.RETROFIT_GSON_CONVERTER)
}

/**
 * Adds dependencies to data module
 */
fun DependencyHandler.addDataModuleDependencies() {
    // Dagger2
    implementation(CommonDeps.DAGGER)
    kapt(CommonDeps.DAGGER_COMPILER)
    kapt(CommonDeps.DAGGER_ANNOTATION_PROCESSOR)

    // Room
    api(DataDeps.ROOM_RUNTIME)
    // For Kotlin use kapt instead of annotationProcessor
    kapt(DataDeps.ROOM_COMPILER)
    // optional - Kotlin Extensions and Coroutines support for Room
    api(DataDeps.ROOM_KTX)

    // Retrofit
    api(NetworkDeps.RETROFIT)

    // Okhttp3
    api(NetworkDeps.OK_HTTP3)
    api(NetworkDeps.OK_HTTP3_LOG)
}

/**
 * Adds dependencies to core module
 */
fun DependencyHandler.addCoreModuleDependencies() {
    // Dagger2
    implementation(CommonDeps.DAGGER)
    kapt(CommonDeps.DAGGER_COMPILER)
    kapt(CommonDeps.DAGGER_ANNOTATION_PROCESSOR)

    // Views, Animations
    api(CommonDeps.PICASSO)

    // SQL
    implementation(DataDeps.SQLCIPHER)
    implementation(DataDeps.SQLITE)
}

/**
 * Adds core dependencies such as kotlin, appcompat, navigation and dagger-hilt to Dynamic
 * Feature modules.
 *
 */
fun DependencyHandler.addBaseDynamicFeatureModuleDependencies() {
    // Dagger2
    implementation(CommonDeps.DAGGER)
    kapt(CommonDeps.DAGGER_COMPILER)
    kapt(CommonDeps.DAGGER_ANNOTATION_PROCESSOR)

    // Leak Canary
    debugImplementation(CommonDeps.LEAK_CANARY)

    // Rounded Image
    implementation(CommonDeps.ROUNDED_IMAGE)

    // Paging
    implementation(CommonDeps.PAGING)
}

/**
 * Adds Unit test dependencies
 */
fun DependencyHandler.addUnitTestDependencies() {
    // (Required) Writing and executing Unit Tests on the JUnit Platform
    testImplementation(TestDeps.JUNIT_DEFAULT)
}

fun DependencyHandler.addInstrumentationTestDependencies() {

    // AndroidX Test - Instrumented testing
    androidTestImplementation(TestDeps.ANDROIDX_JUNIT)
    androidTestImplementation(TestDeps.ANDROIDX_CORE_TESTING)

    // Espresso
    androidTestImplementation(TestDeps.ESPRESSO_CORE)
    androidTestImplementation(TestDeps.ESPRESSO_CONTRIB)
    implementation(TestDeps.ESPRESSO_IDLING_RESOURCE)

    // Testing Navigation
    androidTestImplementation(TestDeps.NAVIGATION_TEST)
}

/*
 * These extensions mimic the extensions that are generated on the fly by Gradle.
 * They are used here to provide above dependency syntax that mimics Gradle Kotlin DSL
 * syntax in module\build.gradle.kts files.
 */
@Suppress("detekt.UnusedPrivateMember")
private fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

@Suppress("detekt.UnusedPrivateMember")
private fun DependencyHandler.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)

@Suppress("detekt.UnusedPrivateMember")
private fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

private fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

private fun DependencyHandler.debugImplementation(dependencyNotation: Any): Dependency? =
    add("debugImplementation", dependencyNotation)

private fun DependencyHandler.testRuntimeOnly(dependencyNotation: Any): Dependency? =
    add("testRuntimeOnly", dependencyNotation)

private fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

private fun DependencyHandler.project(
    path: String,
    configuration: String? = null
): ProjectDependency {
    val notation = if (configuration != null) {
        mapOf("path" to path, "configuration" to configuration)
    } else {
        mapOf("path" to path)
    }
    return uncheckedCast(project(notation))
}

@Suppress("unchecked_cast", "nothing_to_inline", "detekt.UnsafeCast")
private inline fun <T> uncheckedCast(obj: Any?): T = obj as T
