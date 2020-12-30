package com.mhendrif.capstone.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mhendrif.capstone.core.data.source.local.entity.MovieEntity
import com.mhendrif.capstone.core.data.source.local.entity.TvShowEntity

@Database(
    entities = [
        MovieEntity::class,
        TvShowEntity::class,
    ],
    version = 2,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
}