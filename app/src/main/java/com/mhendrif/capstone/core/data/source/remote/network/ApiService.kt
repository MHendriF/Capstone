package com.mhendrif.capstone.core.data.source.remote.network

import com.mhendrif.capstone.core.data.source.remote.response.*
import com.mhendrif.capstone.core.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("discover/movie?api_key=${Constants.TMDB_API_KEY}")
    suspend fun getMovies(): ListMovieResponse

    @GET("discover/tv?api_key=${Constants.TMDB_API_KEY}")
    suspend fun getTvShows(): ListTvShowResponse

    @GET("movie/{id}?api_key=${Constants.TMDB_API_KEY}")
    suspend fun getMovieById(
        @Path("id") id: String
    ): MovieResponse

    @GET("tv/{id}?api_key=${Constants.TMDB_API_KEY}")
    suspend fun getTvShowById(
        @Path("id") id: String
    ): TvShowResponse
}