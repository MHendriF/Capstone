package com.mhendrif.capstone.common.util

enum class SortOrder { BY_NAME, BY_DATE }

object Constants {
    const val TIME_OUT: Long = 120
    const val DELAY: Long = 3000
    const val PREFERENCE_NAME = "user_preference"
    const val DATABASE_NAME = "movie_catalogue_local.db"
    const val MOVIE_TABLE_NAME = "movies"
    const val TV_SHOW_TABLE_NAME = "tvShows"
    const val TMDB_API_KEY = "e2a73685ff4e000b589c681b70c58d0f"
    const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
    const val TMDB_MOVIE_URL = "https://www.themoviedb.org/movie/"
    const val TMDB_TV_URL = "https://www.themoviedb.org/tv/"
    const val API_POSTER_PATH = "https://image.tmdb.org/t/p/w185"
    const val API_BACKDROP_PATH = "https://image.tmdb.org/t/p/w500"
    const val SORT_ORDER = "sort_order"
}