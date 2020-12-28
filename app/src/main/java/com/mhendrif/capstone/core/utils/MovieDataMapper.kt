package com.mhendrif.capstone.core.utils

import com.mhendrif.capstone.core.data.source.local.entity.MovieEntity
import com.mhendrif.capstone.core.data.source.remote.response.MovieResponse
import com.mhendrif.capstone.core.domain.model.Movie

object MovieDataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                    id = it.id,
                    title = it.title,
                    overview = it.overview,
                    posterPath = it.posterPath,
                    backdropPath = it.backdropPath,
                    releaseDate = it.releaseDate,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount,
                    genres = it.genres,
                    homepage = it.homepage,
                    isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
            input.map {
                Movie(
                        id = it.id,
                        title = it.title,
                        overview = it.overview,
                        posterPath = it.posterPath,
                        backdropPath = it.backdropPath,
                        releaseDate = it.releaseDate,
                        voteAverage = it.voteAverage,
                        voteCount = it.voteCount,
                        genres = it.genres,
                        homepage = it.homepage,
                        isFavorite = it.isFavorite
                )
            }

    fun mapDomainToEntity(input: Movie) =
            MovieEntity(
                    id = input.id,
                    title = input.title,
                    overview = input.overview,
                    posterPath = input.posterPath,
                    backdropPath = input.backdropPath,
                    releaseDate = input.releaseDate,
                    voteAverage = input.voteAverage,
                    voteCount = input.voteCount,
                    genres = input.genres,
                    homepage = input.homepage,
                    isFavorite = input.isFavorite
            )

    fun mapResponseToEntity(input: MovieResponse): MovieEntity {
        return MovieEntity(
                id = input.id,
                title = input.title,
                overview = input.overview,
                posterPath = input.posterPath,
                backdropPath = input.backdropPath,
                releaseDate = input.releaseDate,
                voteAverage = input.voteAverage,
                voteCount = input.voteCount,
                genres = input.genres,
                homepage = input.homepage,
                isFavorite = false
        )
    }

    fun mapEntityToDomain(input: MovieEntity): Movie {
        return Movie(
                id = input.id,
                title = input.title,
                overview = input.overview,
                posterPath = input.posterPath,
                backdropPath = input.backdropPath,
                releaseDate = input.releaseDate,
                voteAverage = input.voteAverage,
                voteCount = input.voteCount,
                genres = input.genres,
                homepage = input.homepage,
                isFavorite = input.isFavorite
        )
    }
}