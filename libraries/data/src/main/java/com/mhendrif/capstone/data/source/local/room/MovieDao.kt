package com.mhendrif.capstone.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mhendrif.capstone.common.util.SortOrder
import com.mhendrif.capstone.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    fun getMovies(sortOrder: SortOrder): Flow<List<MovieEntity>> =
        when (sortOrder) {
            SortOrder.BY_DATE -> getMoviesSortedByReleaseDate()
            SortOrder.BY_NAME -> getMoviesSortedByName()
        }

    @Query("SELECT * FROM movies WHERE isFavorite = 1 ORDER BY title ASC")
    fun getMoviesSortedByName(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE isFavorite = 1 ORDER BY release_date ASC")
    fun getMoviesSortedByReleaseDate(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies")
    fun getAllMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies where isFavorite = 1")
    fun getFavorite(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovie(movies: List<MovieEntity>)

    @Update
    suspend fun update(movie: MovieEntity)

    @Update
    fun updateFavorite(movie: MovieEntity)

    @Query("SELECT * FROM movies where id=:id")
    fun getDetailMovie(id: Int): Flow<MovieEntity>
}
