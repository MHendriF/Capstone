package com.mhendrif.capstone.core.data.repository

import com.mhendrif.capstone.core.data.Resource
import com.mhendrif.capstone.core.data.source.local.LocalDataSource
import com.mhendrif.capstone.core.data.source.remote.RemoteDataSource
import com.mhendrif.capstone.core.domain.model.Movie
import com.mhendrif.capstone.core.domain.repository.IMovieRepository
import com.mhendrif.capstone.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository{

    override fun getAllMovie(): Flow<Resource<List<Movie>>> {
        TODO("Not yet implemented")
    }

    override fun getFavorite(): Flow<List<Movie>> {
        TODO("Not yet implemented")
    }

    override fun setFavorite(movie: Movie, state: Boolean) {
        TODO("Not yet implemented")
    }
}