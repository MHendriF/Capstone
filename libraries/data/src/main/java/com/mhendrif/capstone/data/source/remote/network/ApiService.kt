package com.mhendrif.capstone.data.source.remote.network

import com.mhendrif.capstone.common.util.Constants
import com.mhendrif.capstone.data.source.remote.response.ListMovieResponse
import com.mhendrif.capstone.data.source.remote.response.ListTvShowResponse
import com.mhendrif.capstone.data.source.remote.response.MovieResponse
import com.mhendrif.capstone.data.source.remote.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("discover/movie?api_key=${Constants.TMDB_API_KEY}&language=en-US")
    suspend fun getMovies(): ListMovieResponse

    @GET("discover/tv?api_key=${Constants.TMDB_API_KEY}&language=en-US")
    suspend fun getTvShows(): ListTvShowResponse

    @GET("movie/{id}?api_key=${Constants.TMDB_API_KEY}")
    suspend fun getMovieById(
        @Path("id") id: Int
    ): MovieResponse

    @GET("tv/{id}?api_key=${Constants.TMDB_API_KEY}")
    suspend fun getTvShowById(
        @Path("id") id: Int
    ): TvShowResponse
}