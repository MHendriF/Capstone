package com.mhendrif.capstone.core.data.source.remote

import com.mhendrif.capstone.core.data.source.remote.network.ApiResponse
import com.mhendrif.capstone.core.data.source.remote.network.ApiService
import com.mhendrif.capstone.core.data.source.remote.response.MovieResponse
import com.mhendrif.capstone.core.data.source.remote.response.TvShowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllMovie(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovies()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    Timber.d("Timber - succ")
                    emit(ApiResponse.Success(response.results))
                } else {
                    Timber.d("Timber - empt")
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Timber.e(e.toString())
                Timber.d("Timber - catch")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllTvShow(): Flow<ApiResponse<List<TvShowResponse>>> {
        return flow {
            try {
                val response = apiService.getTvShows()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Timber.e(e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailMovie(id: Int): Flow<ApiResponse<MovieResponse>> {
        return flow {
            try {
                Timber.d("Timber detail - succ")
                val response = apiService.getMovieById(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Timber.d("Timber detail - err")
                Timber.e(e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailMovie2(id: Int): Flow<MovieResponse> {
        return flow {
            try {
                val response = apiService.getMovieById(id)
                emit(response)
            } catch (e: Exception) {
                Timber.e(e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailTvShow(id: Int): Flow<ApiResponse<TvShowResponse>> {
        return flow {
            try {
                val response = apiService.getTvShowById(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Timber.e(e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}