package extension

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * Adds required dependencies to app module
 */
fun DependencyHandler.addAppModuleDependencies() {
    implementation(CommonDeps.KOTLIN)
    implementation(CommonDeps.ANDROIDX_CORE_KTX)

    // Support and Widgets
    implementation(CommonDeps.APPCOMPAT)
    implementation(CommonDeps.MATERIAL)
    implementation(CommonDeps.CONSTRAINT_LAYOUT)
    implementation(CommonDeps.LEGACY)

    // Views, Animations
    implementation(CommonDeps.LOTTIE)
    implementation(CommonDeps.SSP_ANDROID)
    implementation(CommonDeps.SDP_ANDROID)

    implementation(CommonDeps.PICASSO)
    implementation(CommonDeps.TIMBER)
    implementation(CommonDeps.MULTIDEX)

    // Navigation Components
    implementation(CommonDeps.NAVIGATION_FRAGMENT)
    implementation(CommonDeps.NAVIGATION_UI)
    implementation(CommonDeps.NAVIGATION_RUNTIME)
    implementation(CommonDeps.NAVIGATION_DYNAMIC)

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
 * Adds dependencies to common module
 */
fun DependencyHandler.addCommonModuleDependencies() {
    implementation(CommonDeps.KOTLIN)
    implementation(CommonDeps.ANDROIDX_CORE_KTX)
    implementation(CommonDeps.TIMBER)

    // Support and Widgets
    implementation(CommonDeps.APPCOMPAT)
    implementation(CommonDeps.MATERIAL)
    implementation(CommonDeps.CONSTRAINT_LAYOUT)
    implementation(CommonDeps.LEGACY)

    // Views, Animations
    implementation(CommonDeps.LOTTIE)
    implementation(CommonDeps.SSP_ANDROID)
    implementation(CommonDeps.SDP_ANDROID)
}

/**
 * Adds dependencies to core module
 */
fun DependencyHandler.addCoreModuleDependencies() {
    implementation(CommonDeps.KOTLIN)
    implementation(CommonDeps.ANDROIDX_CORE_KTX)

    // Support and Widgets
    implementation(CommonDeps.APPCOMPAT)
    implementation(CommonDeps.MATERIAL)
    implementation(CommonDeps.CONSTRAINT_LAYOUT)
    implementation(CommonDeps.LEGACY)

    // Navigation Components
    implementation(CommonDeps.NAVIGATION_FRAGMENT)
    implementation(CommonDeps.NAVIGATION_UI)
    implementation(CommonDeps.NAVIGATION_RUNTIME)
    implementation(CommonDeps.NAVIGATION_DYNAMIC)

    // Views, Animations
    implementation(CommonDeps.LOTTIE)
    implementation(CommonDeps.SSP_ANDROID)
    implementation(CommonDeps.SDP_ANDROID)

    implementation(CommonDeps.PICASSO)
    implementation(CommonDeps.TIMBER)
    implementation(CommonDeps.MULTIDEX)

    // Rounded Image
    implementation(CommonDeps.ROUNDED_IMAGE)

    // Coroutines
    implementation(CommonDeps.COROUTINES_CORE)
    implementation(CommonDeps.COROUTINES_ANDROID)

    // Dagger2
    implementation(CommonDeps.DAGGER)
    kapt(CommonDeps.DAGGER_COMPILER)
    kapt(CommonDeps.DAGGER_ANNOTATION_PROCESSOR)

    // Lifecycle, LiveData, ViewModel
    api(CommonDeps.LIFECYCLE_LIVEDATA_KTX)
    api(CommonDeps.LIFECYCLE_VIEWMODEL_KTX)
    api(CommonDeps.LIFECYCLE_EXTENSIONS)

    // Room
    api(DataDeps.ROOM_RUNTIME)
    // For Kotlin use kapt instead of annotationProcessor
    kapt(DataDeps.ROOM_COMPILER)
    // optional - Kotlin Extensions and Coroutines support for Room
    api(DataDeps.ROOM_KTX)

    api(DataDeps.SQLCIPHER)
    api(DataDeps.SQLITE)

    // Retrofit
    implementation(NetworkDeps.RETROFIT)
    implementation(NetworkDeps.RETROFIT_GSON_CONVERTER)

    // Okhttp3
    implementation(NetworkDeps.OK_HTTP3)
    implementation(NetworkDeps.OK_HTTP3_LOG)

    // Paging
    implementation(CommonDeps.PAGING)
}

/**
 * Adds dependencies to data module
 */
fun DependencyHandler.addDataModuleDependencies() {
    implementation(CommonDeps.KOTLIN)
    implementation(CommonDeps.ANDROIDX_CORE_KTX)

    implementation(CommonDeps.TIMBER)
    implementation(CommonDeps.MULTIDEX)

    // Coroutines
    implementation(CommonDeps.COROUTINES_CORE)
    implementation(CommonDeps.COROUTINES_ANDROID)

    // Dagger2
    implementation(CommonDeps.DAGGER)
    kapt(CommonDeps.DAGGER_COMPILER)
    kapt(CommonDeps.DAGGER_ANNOTATION_PROCESSOR)

    // Lifecycle, LiveData, ViewModel
    api(CommonDeps.LIFECYCLE_LIVEDATA_KTX)
    api(CommonDeps.LIFECYCLE_VIEWMODEL_KTX)
    api(CommonDeps.LIFECYCLE_EXTENSIONS)

    // Room
    api(DataDeps.ROOM_RUNTIME)
    // For Kotlin use kapt instead of annotationProcessor
    kapt(DataDeps.ROOM_COMPILER)
    // optional - Kotlin Extensions and Coroutines support for Room
    api(DataDeps.ROOM_KTX)

    // Retrofit
    implementation(NetworkDeps.RETROFIT)
    implementation(NetworkDeps.RETROFIT_GSON_CONVERTER)

    // Okhttp3
    implementation(NetworkDeps.OK_HTTP3)
    implementation(NetworkDeps.OK_HTTP3_LOG)

    // Paging
    implementation(CommonDeps.PAGING)
}

/**
 * Adds dependencies to domain module
 */
fun DependencyHandler.addDomainModuleDependencies() {
    implementation(CommonDeps.KOTLIN)
    implementation(CommonDeps.ANDROIDX_CORE_KTX)

    // Coroutines
    implementation(CommonDeps.COROUTINES_CORE)
    implementation(CommonDeps.COROUTINES_ANDROID)

    // Dagger2
    implementation(CommonDeps.DAGGER)
    kapt(CommonDeps.DAGGER_COMPILER)
    kapt(CommonDeps.DAGGER_ANNOTATION_PROCESSOR)

    // Lifecycle, LiveData, ViewModel
    api(CommonDeps.LIFECYCLE_LIVEDATA_KTX)
    api(CommonDeps.LIFECYCLE_VIEWMODEL_KTX)
    api(CommonDeps.LIFECYCLE_EXTENSIONS)

    // Room
    api(DataDeps.ROOM_RUNTIME)
    // For Kotlin use kapt instead of annotationProcessor
    kapt(DataDeps.ROOM_COMPILER)
    // optional - Kotlin Extensions and Coroutines support for Room
    api(DataDeps.ROOM_KTX)

    // Retrofit
    implementation(NetworkDeps.RETROFIT)
    implementation(NetworkDeps.RETROFIT_GSON_CONVERTER)

    // Paging
    implementation(CommonDeps.PAGING)
}

/**
 * Adds core dependencies such as kotlin, appcompat, navigation and dagger-hilt to Dynamic
 * Feature modules.
 *
 */
fun DependencyHandler.addBaseDynamicFeatureModuleDependencies() {
    implementation(CommonDeps.KOTLIN)
    implementation(CommonDeps.ANDROIDX_CORE_KTX)

    // Support and Widgets
    implementation(CommonDeps.APPCOMPAT)
    implementation(CommonDeps.MATERIAL)
    implementation(CommonDeps.CONSTRAINT_LAYOUT)
    implementation(CommonDeps.LEGACY)

    // Lifecycle, LiveData, ViewModel
    implementation(CommonDeps.LIFECYCLE_LIVEDATA_KTX)
    implementation(CommonDeps.LIFECYCLE_VIEWMODEL_KTX)
    implementation(CommonDeps.LIFECYCLE_EXTENSIONS)

    // Navigation Components
    implementation(CommonDeps.NAVIGATION_FRAGMENT)
    implementation(CommonDeps.NAVIGATION_UI)
    implementation(CommonDeps.NAVIGATION_RUNTIME)
    implementation(CommonDeps.NAVIGATION_DYNAMIC)
    // Dagger2
    implementation(CommonDeps.DAGGER)
    kapt(CommonDeps.DAGGER_COMPILER)
    kapt(CommonDeps.DAGGER_ANNOTATION_PROCESSOR)

    // Coroutines
    implementation(CommonDeps.COROUTINES_CORE)
    implementation(CommonDeps.COROUTINES_ANDROID)

    // Views, Animations
    implementation(CommonDeps.LOTTIE)
    implementation(CommonDeps.SSP_ANDROID)
    implementation(CommonDeps.SDP_ANDROID)

    implementation(CommonDeps.PICASSO)
    implementation(CommonDeps.TIMBER)
    implementation(CommonDeps.MULTIDEX)

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
