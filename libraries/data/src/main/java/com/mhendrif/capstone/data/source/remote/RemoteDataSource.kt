package com.mhendrif.capstone.data.source.remote

import com.mhendrif.capstone.data.source.remote.network.ApiResponse
import com.mhendrif.capstone.data.source.remote.network.ApiService
import com.mhendrif.capstone.data.source.remote.response.MovieResponse
import com.mhendrif.capstone.data.source.remote.response.TvShowResponse
import com.mhendrif.capstone.data.util.EspressoIdlingResource
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllMovie(): Flow<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = apiService.getMovies()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                    EspressoIdlingResource.decrement()
                } else {
                    emit(ApiResponse.Empty)
                    EspressoIdlingResource.decrement()
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                EspressoIdlingResource.decrement()
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllTvShow(): Flow<ApiResponse<List<TvShowResponse>>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = apiService.getTvShows()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                    EspressoIdlingResource.decrement()
                } else {
                    emit(ApiResponse.Empty)
                    EspressoIdlingResource.decrement()
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                EspressoIdlingResource.decrement()
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailMovie(id: Int): Flow<ApiResponse<MovieResponse>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = apiService.getMovieById(id)
                emit(ApiResponse.Success(response))
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                EspressoIdlingResource.decrement()
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailTvShow(id: Int): Flow<ApiResponse<TvShowResponse>> {
        EspressoIdlingResource.increment()
        return flow {
            try {
                val response = apiService.getTvShowById(id)
                emit(ApiResponse.Success(response))
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                EspressoIdlingResource.decrement()
            }
        }.flowOn(Dispatchers.IO)
    }
}
