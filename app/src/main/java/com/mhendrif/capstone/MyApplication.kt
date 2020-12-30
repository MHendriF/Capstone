package com.mhendrif.capstone

import android.app.Application
import com.mhendrif.capstone.core.di.databaseModule
import com.mhendrif.capstone.core.di.networkModule
import com.mhendrif.capstone.core.di.repositoryModule
import com.mhendrif.capstone.core.utils.ReleaseTree
import com.mhendrif.capstone.di.useCaseModule
import com.mhendrif.capstone.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }
}