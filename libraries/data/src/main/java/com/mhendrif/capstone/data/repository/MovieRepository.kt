package com.mhendrif.capstone.data.repository

import com.mhendrif.capstone.common.util.SortOrder
import com.mhendrif.capstone.data.NetworkBoundResource
import com.mhendrif.capstone.domain.Resource
import com.mhendrif.capstone.data.mapper.MovieDataMapper
import com.mhendrif.capstone.data.source.local.LocalDataSource
import com.mhendrif.capstone.data.source.remote.RemoteDataSource
import com.mhendrif.capstone.data.source.remote.network.ApiResponse
import com.mhendrif.capstone.data.source.remote.response.MovieResponse
import com.mhendrif.capstone.data.util.AppExecutors
import com.mhendrif.capstone.domain.model.Movie
import com.mhendrif.capstone.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map {
                    MovieDataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovie()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val dataList = MovieDataMapper.mapResponsesToEntities(data)
                localDataSource.insertAllMovie(dataList)
            }
        }.asFlow()

    override fun getFavorite(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map {
            MovieDataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavorite(movie: Movie, state: Boolean) {
        val entity = MovieDataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(entity, state) }
    }

    override fun getDetailMovie(id: Int): Flow<Resource<Movie>> =
        object : NetworkBoundResource<Movie, MovieResponse>(appExecutors) {
            override fun loadFromDB(): Flow<Movie> {
                return localDataSource.getDetailMovie(id).map {
                    MovieDataMapper.mapEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: Movie?): Boolean =
                data?.genres == null || data.genres!!.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<MovieResponse>> =
                remoteDataSource.getDetailMovie(id)

            override suspend fun saveCallResult(data: MovieResponse) {
                val entity = MovieDataMapper.mapResponseToEntity(data)
                localDataSource.updateMovie(entity)
            }
        }.asFlow()

    override fun getFavoriteBySort(sortOrder: SortOrder): Flow<List<Movie>> {
        return localDataSource.getMoviesOnSortOrderSelected(sortOrder).map {
            MovieDataMapper.mapEntitiesToDomain(it)
        }
    }
}
