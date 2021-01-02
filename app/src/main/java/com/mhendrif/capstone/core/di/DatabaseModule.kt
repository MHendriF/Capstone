package com.mhendrif.capstone.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.room.Room
import com.mhendrif.capstone.core.data.source.local.room.AppDatabase
import com.mhendrif.capstone.core.data.source.local.room.MovieDao
import com.mhendrif.capstone.core.data.source.local.room.TvShowDao
import com.mhendrif.capstone.core.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context, AppDatabase::class.java, Constants.DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideMovieDao(database: AppDatabase): MovieDao = database.movieDao()

    @Provides
    fun provideTvShowDao(database: AppDatabase): TvShowDao = database.tvShowDao()

    @Singleton
    @Provides
    fun provideMyPreferenceManager(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.createDataStore(Constants.PREFERENCE_NAME)
    }
}