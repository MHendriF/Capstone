package com.mhendrif.capstone.core.data.source.local.room

import androidx.room.*
import com.mhendrif.capstone.core.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TvShowDao {
    @Query("SELECT * FROM tvShows")
    fun getAllTvShow(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tvShows where isFavorite = 1")
    fun getFavorite(): Flow<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTvShow(tvShow: List<TvShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvShow: TvShowEntity)

    @Update
    suspend fun update(tvShow: TvShowEntity)

    @Delete
    suspend fun delete(tvShow: TvShowEntity)

    @Update
    fun updateFavorite(tvShow: TvShowEntity)

    @Query("SELECT * FROM tvShows where id=:id")
    fun getDetailTvShow(id: Int): Flow<TvShowEntity>
}