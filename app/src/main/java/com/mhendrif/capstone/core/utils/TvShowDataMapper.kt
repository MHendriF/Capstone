package com.mhendrif.capstone.core.utils

import com.mhendrif.capstone.core.data.source.local.entity.TvShowEntity
import com.mhendrif.capstone.core.data.source.remote.response.TvShowResponse
import com.mhendrif.capstone.core.domain.model.TvShow

object TvShowDataMapper {
    fun mapResponsesToEntities(input: List<TvShowResponse>): List<TvShowEntity> {
        val dataList = ArrayList<TvShowEntity>()
        input.map {
            val tvShow = TvShowEntity(
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
            dataList.add(tvShow)
        }
        return dataList
    }

    fun mapEntitiesToDomain(input: List<TvShowEntity>): List<TvShow> =
            input.map {
                TvShow(
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

    fun mapDomainToEntity(input: TvShow) =
            TvShowEntity(
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

    fun mapResponseToEntity(input: TvShowResponse): TvShowEntity {
        return TvShowEntity(
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

    fun mapEntityToDomain(input: TvShowEntity): TvShow {
        return TvShow(
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