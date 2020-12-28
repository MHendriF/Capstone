package com.mhendrif.capstone.core.data.source.local

import com.mhendrif.capstone.core.data.source.local.entity.MovieEntity
import com.mhendrif.capstone.core.data.source.local.entity.TvShowEntity
import com.mhendrif.capstone.core.data.source.local.room.MovieDao
import com.mhendrif.capstone.core.data.source.local.room.TvShowDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao, private val tvShowDao: TvShowDao) {

    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavorite()

    suspend fun insertMovie(movieEntity: MovieEntity) = movieDao.insert(movieEntity)

    suspend fun updateMovie(movieEntity: MovieEntity) = movieDao.update(movieEntity)

    suspend fun deleteMovie(movieEntity: MovieEntity) = movieDao.delete(movieEntity)

    suspend fun setFavoriteMovie(movieEntity: MovieEntity, newState: Boolean) {
        movieEntity.isFavorite = newState
        movieDao.update(movieEntity)
    }

    fun getAllTvShow(): Flow<List<TvShowEntity>> = tvShowDao.getAllTvShow()

    fun getFavoriteTvShow(): Flow<List<TvShowEntity>> = tvShowDao.getFavorite()

    suspend fun insertTvShow(tvShowEntity: TvShowEntity) = tvShowDao.insert(tvShowEntity)

    suspend fun updateTvShow(tvShowEntity: TvShowEntity) = tvShowDao.update(tvShowEntity)

    suspend fun deleteTvShow(tvShowEntity: TvShowEntity) = tvShowDao.delete(tvShowEntity)

    suspend fun setFavoriteTvShow(tvShowEntity: TvShowEntity, newState: Boolean) {
        tvShowEntity.isFavorite = newState
        tvShowDao.update(tvShowEntity)
    }
}