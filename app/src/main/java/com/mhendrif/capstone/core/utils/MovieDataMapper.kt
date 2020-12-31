package com.mhendrif.capstone.core.utils

import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import com.mhendrif.capstone.core.data.source.local.entity.MovieEntity
import com.mhendrif.capstone.core.data.source.remote.response.MovieResponse
import com.mhendrif.capstone.core.domain.model.Genre
import com.mhendrif.capstone.core.domain.model.Movie

object MovieDataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            var listGenre = ""
            if (it.genres != null && it.genres.isNotEmpty()) {
                listGenre = Gson().toJson(it.genres)
            }
            val movie = MovieEntity(
                    id = it.id,
                    title = it.title,
                    overview = it.overview,
                    posterPath = it.posterPath,
                    backdropPath = it.backdropPath,
                    releaseDate = it.releaseDate,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount,
                    genres = listGenre,
                    homepage = it.homepage ?: "",
                    isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapResponseToEntity(input: MovieResponse): MovieEntity {
        var listGenre = ""
        if (input.genres != null && input.genres.isNotEmpty()) {
            listGenre = Gson().toJson(input.genres)
        }
        return MovieEntity(
            id = input.id,
            title = input.title,
            overview = input.overview,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            releaseDate = input.releaseDate,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            genres = listGenre,
            homepage = input.homepage ?: "",
            isFavorite = false
        )
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
            input.map {
                val listGenre = ArrayList<Genre>()
                if (it.genres != null && it.genres?.isNotEmpty()!!) {
                    listGenre.addAll(setUpGenre(it.genres!!))
                }
                Movie(
                        id = it.id,
                        title = it.title,
                        overview = it.overview,
                        posterPath = it.posterPath,
                        backdropPath = it.backdropPath,
                        releaseDate = it.releaseDate,
                        voteAverage = it.voteAverage,
                        voteCount = it.voteCount,
                        genres = listGenre,
                        homepage = it.homepage,
                        isFavorite = it.isFavorite
                )
            }

    fun mapEntityToDomain(input: MovieEntity): Movie {
        val listGenre = ArrayList<Genre>()
        if (input.genres != null && input.genres?.isNotEmpty()!!) {
            listGenre.addAll(setUpGenre(input.genres!!))
        }
        return Movie(
            id = input.id,
            title = input.title,
            overview = input.overview,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            releaseDate = input.releaseDate,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            genres = listGenre,
            homepage = input.homepage,
            isFavorite = input.isFavorite
        )
    }

    fun mapDomainToEntity(input: Movie): MovieEntity {
        var listGenre = ""
        if (input.genres != null && input.genres.isNotEmpty()) {
            listGenre = Gson().toJson(input.genres)
        }
        return MovieEntity(
            id = input.id,
            title = input.title,
            overview = input.overview,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            releaseDate = input.releaseDate,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            genres = listGenre,
            homepage = input.homepage,
            isFavorite = input.isFavorite
        )
    }

    private fun setUpGenre(genreStr: String): List<Genre> {
        val listGenre = ArrayList<Genre>()
        val obj = Gson().fromJson(genreStr, List::class.java)
        for (genreObjStr in obj) {
            val genreObj = genreObjStr as LinkedTreeMap<*, *>
            val genreId: Double = (genreObj["id"] ?: 0) as Double
            val genre = Genre(genreId.toInt(), (genreObj["name"] ?: "") as String)
            listGenre.add(genre)
        }
        return listGenre
    }
}