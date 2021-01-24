package com.mhendrif.capstone.common.util

import com.mhendrif.capstone.common.BuildConfig

enum class SortOrder { BY_NAME, BY_DATE }

object Constants {
    const val TIME_OUT: Long = 120
    const val DELAY: Long = 2500
    const val DATABASE_NAME = "movie_catalogue_local.db"
    const val MOVIE_TABLE_NAME = "movies"
    const val TV_SHOW_TABLE_NAME = "tvShows"
    const val TMDB_API_KEY = BuildConfig.TMDB_API_KEY
    const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
    const val TMDB_MOVIE_URL = "https://www.themoviedb.org/movie/"
    const val TMDB_TV_URL = "https://www.themoviedb.org/tv/"
    const val API_POSTER_PATH = "https://image.tmdb.org/t/p/w185"
    const val API_BACKDROP_PATH = "https://image.tmdb.org/t/p/w500"
    const val ARG_FAVORITE_MOVIE = "FAVORITE_MOVIE_FRAGMENT"
    const val ARG_FAVORITE_TV = "FAVORITE_TV_FRAGMENT"
    const val PASSPHRASE = BuildConfig.DB_PASSPHRASE
    const val HOSTNAME = "www.themoviedb.org"
}
