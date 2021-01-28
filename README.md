# Capstone

Simple app movie catalogue using API from [The Movie DB](https://www.themoviedb.org)

[![CircleCI](https://circleci.com/gh/circleci/circleci-docs.svg?style=shield)](https://circleci.com/gh/MHendriF/Capstone)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)
[![Kotlin Version](https://img.shields.io/badge/kotlin-1.4.21-blue.svg)](https://kotlinlang.org)

## Notes 📝
Before you run this application please config environment in file **local.properties**
```kotlin
API_KEY="YOUR_API_KEY"
PASSPHRASE="YOUR_DB_PASSPHRASE"
```
[How to get Api Key](https://developers.themoviedb.org/3/getting-started/introduction)

## Features 🕹

- Following [Clean Architecture approach](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- Following [MVVM Architectural Design Pattern](https://developer.android.com/jetpack/guide)
- Circle CI - [Continuous Integration](https://www.atlassian.com/continuous-delivery/continuous-integration)
- [Hilt](https://dagger.dev/hilt) - Dependency Injection framework
- [Data Binding](https://developer.android.com/topic/libraries/data-binding) - Data Binding
- [Room](https://developer.android.com/training/data-storage/room) - Persistence library provides an abstraction layer over SQLite
- [OkHttp3](https://github.com/square/okhttp) - Network interceptor
- [Retrofit](https://github.com/square/retrofit) - HTTP client
- [Picasso](https://github.com/square/picasso) - Loading images
- [Timber](https://github.com/JakeWharton/timber) - Log
- [Gson](https://github.com/google/gson) - JSON library
- [Material Components](https://github.com/material-components/material-components-android) - Material Design
- [Lottie](https://airbnb.design/lottie/) - Vector animation library
- [Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - Alternative syntax to the Groovy DSL
- [Detekt](https://github.com/detekt/detekt) - Static code analysis for Kotlin
- [Ktlint](https://ktlint.github.io/) - Kotlin linter with built-in formatter
- [Navigation Component](https://developer.android.com/guide/navigation) - Navigate through the app
- [LeakCanary](https://square.github.io/leakcanary/) - Memory leak detection
- [Dynamic Feature Module](https://developer.android.com/guide/app-bundle/play-feature-delivery) - Custom delivery module
- [SQLCipher](https://github.com/sqlcipher/android-database-sqlcipher) - Encryption data
- [CertificatePinner](https://square.github.io/okhttp/4.x/okhttp/okhttp3/-certificate-pinner/) - Secure the network
- [Coroutine + flow](https://developer.android.com/kotlin/coroutines) - Dynamic programming
- [ProGuard](https://medium.com/androiddevelopers/troubleshooting-proguard-issues-on-android-bce9de4f8a74) - Obfuscate the app
- [Firebase Crashlytics](https://medium.com/androiddevelopers/troubleshooting-proguard-issues-on-android-bce9de4f8a74) - Realtime crash reporter

## Future Development 🚧

- [ ] Replace Data Binding with [View Binding](https://developer.android.com/topic/libraries/view-binding)
- [x] Migrate from [Dagger](https://github.com/google/dagger) to [Hilt](https://dagger.dev/hilt/) as Dependency Injection
- [ ] Add feature search
- [ ] Use [Paging](https://developer.android.com/topic/libraries/architecture/paging) for management data on RecyclerView
- [ ] Add Unit Testing and Instrument Testing
- [ ] Use [Jetpack Compose](https://developer.android.com/jetpack/compose) for UI toolkit

## MAD Score

<img src="assets/summary.png" />

## Author 👨‍🍳

_M Hendri Febriansyah_
