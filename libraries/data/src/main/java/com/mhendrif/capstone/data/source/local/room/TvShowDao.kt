package com.mhendrif.capstone.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mhendrif.capstone.common.util.SortOrder
import com.mhendrif.capstone.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TvShowDao {
    fun getTvShows(sortOrder: SortOrder): Flow<List<TvShowEntity>> =
        when (sortOrder) {
            SortOrder.BY_DATE -> getTvShowsSortedByReleaseDate()
            SortOrder.BY_NAME -> getTvShowsSortedByName()
        }

    @Query("SELECT * FROM tvShows WHERE isFavorite = 1 ORDER BY title ASC")
    fun getTvShowsSortedByReleaseDate(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tvShows WHERE isFavorite = 1 ORDER BY release_date ASC")
    fun getTvShowsSortedByName(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tvShows")
    fun getAllTvShow(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tvShows where isFavorite = 1")
    fun getFavorite(): Flow<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTvShow(tvShow: List<TvShowEntity>)

    @Update
    suspend fun update(tvShow: TvShowEntity)

    @Update
    fun updateFavorite(tvShow: TvShowEntity)

    @Query("SELECT * FROM tvShows where id=:id")
    fun getDetailTvShow(id: Int): Flow<TvShowEntity>
}
