package com.mhendrif.capstone.data.source.local

import com.mhendrif.capstone.common.util.SortOrder
import com.mhendrif.capstone.data.source.local.entity.MovieEntity
import com.mhendrif.capstone.data.source.local.entity.TvShowEntity
import com.mhendrif.capstone.data.source.local.room.MovieDao
import com.mhendrif.capstone.data.source.local.room.TvShowDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val movieDao: MovieDao,
    private val tvShowDao: TvShowDao
) {

    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavorite()

    suspend fun insertAllMovie(movieList: List<MovieEntity>) = movieDao.insertAllMovie(movieList)

    suspend fun updateMovie(movieEntity: MovieEntity) = movieDao.update(movieEntity)

    fun setFavoriteMovie(movieEntity: MovieEntity, newState: Boolean) {
        movieEntity.isFavorite = newState
        movieDao.updateFavorite(movieEntity)
    }

    fun getDetailMovie(id: Int) = movieDao.getDetailMovie(id)

    fun getAllTvShow(): Flow<List<TvShowEntity>> = tvShowDao.getAllTvShow()

    fun getFavoriteTvShow(): Flow<List<TvShowEntity>> = tvShowDao.getFavorite()

    suspend fun insertAllTvShow(tvShowList: List<TvShowEntity>) =
        tvShowDao.insertAllTvShow(tvShowList)

    suspend fun updateTvShow(tvShowEntity: TvShowEntity) = tvShowDao.update(tvShowEntity)

    fun setFavoriteTvShow(tvShowEntity: TvShowEntity, newState: Boolean) {
        tvShowEntity.isFavorite = newState
        tvShowDao.updateFavorite(tvShowEntity)
    }

    fun getDetailTvShow(id: Int) = tvShowDao.getDetailTvShow(id)

    fun getMoviesOnSortOrderSelected(sortOrder: SortOrder) = movieDao.getMovies(sortOrder)

    fun getTvShowsOnSortOrderSelected(sortOrder: SortOrder) = tvShowDao.getTvShows(sortOrder)
}