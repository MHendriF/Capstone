package com.mhendrif.capstone.core.di

import android.content.Context
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import androidx.room.Room
import com.mhendrif.capstone.common.util.Constants
import com.mhendrif.capstone.data.source.local.room.AppDatabase
import com.mhendrif.capstone.data.source.local.room.MovieDao
import com.mhendrif.capstone.data.source.local.room.TvShowDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes(Constants.PASSPHRASE.toCharArray())
        val sqlCipherFactory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            Constants.DATABASE_NAME
        ).fallbackToDestructiveMigration().openHelperFactory(sqlCipherFactory).build()
    }

    @Provides
    fun provideMovieDao(database: AppDatabase): MovieDao = database.movieDao()

    @Provides
    fun provideTvShowDao(database: AppDatabase): TvShowDao = database.tvShowDao()
}
