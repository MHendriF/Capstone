package com.mhendrif.capstone.data.mapper

import com.mhendrif.capstone.data.source.local.entity.MovieEntity
import com.mhendrif.capstone.data.source.remote.response.MovieResponse
import com.mhendrif.capstone.data.util.GenreConverter
import com.mhendrif.capstone.domain.model.Genre
import com.mhendrif.capstone.domain.model.Movie

object MovieDataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            var listGenre = ""
            if (it.genres != null && it.genres.isNotEmpty()) {
                listGenre = GenreConverter.toString(it.genres)
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
                homepage = it.homepage,
                runtime = it.runtime,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapResponseToEntity(input: MovieResponse): MovieEntity {
        var listGenre = ""
        if (input.genres != null && input.genres.isNotEmpty()) {
            listGenre = GenreConverter.toString(input.genres)
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
            runtime = input.runtime,
            isFavorite = false
        )
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> = input.map {
        var listGenre = ArrayList<Genre>()
        if (it.genres.isNotEmpty()) {
            listGenre = GenreConverter.toListGenre(it.genres) as ArrayList<Genre>
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
            runtime = it.runtime,
            isFavorite = it.isFavorite
        )
    }

    fun mapEntityToDomain(input: MovieEntity): Movie {
        var listGenre = ArrayList<Genre>()
        if (input.genres.isNotEmpty()) {
            listGenre = GenreConverter.toListGenre(input.genres) as ArrayList<Genre>
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
            runtime = input.runtime,
            isFavorite = input.isFavorite
        )
    }

    fun mapDomainToEntity(input: Movie): MovieEntity {
        var listGenre = ""
        if (input.genres != null && input.genres!!.isNotEmpty()) {
            listGenre = GenreConverter.toString(input.genres!!)
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
            runtime = input.runtime,
            isFavorite = input.isFavorite
        )
    }
}
