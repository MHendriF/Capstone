package com.mhendrif.capstone.data.mapper

import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import com.mhendrif.capstone.data.source.local.entity.TvShowEntity
import com.mhendrif.capstone.data.source.remote.response.TvShowResponse
import com.mhendrif.capstone.domain.model.Genre
import com.mhendrif.capstone.domain.model.TvShow

object TvShowDataMapper {
    fun mapResponsesToEntities(input: List<TvShowResponse>): List<TvShowEntity> {
        val dataList = ArrayList<TvShowEntity>()
        input.map {
            var listGenre = ""
            if (it.genres != null && it.genres.isNotEmpty()) {
                listGenre = Gson().toJson(it.genres)
            }
            val tvShow = TvShowEntity(
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
                numberOfSeasons = it.numberOfSeasons,
                isFavorite = false
            )
            dataList.add(tvShow)
        }
        return dataList
    }

    fun mapResponseToEntity(input: TvShowResponse): TvShowEntity {
        var listGenre = ""
        if (input.genres != null && input.genres.isNotEmpty()) {
            listGenre = Gson().toJson(input.genres)
        }
        return TvShowEntity(
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
            numberOfSeasons = input.numberOfSeasons,
            isFavorite = false
        )
    }

    fun mapEntitiesToDomain(input: List<TvShowEntity>): List<TvShow> =
            input.map {
                val listGenre = ArrayList<Genre>()
                if (it.genres != null && it.genres?.isNotEmpty()!!) {
                    listGenre.addAll(setUpGenre(it.genres!!))
                }
                TvShow(
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
                    numberOfSeasons = it.numberOfSeasons,
                    isFavorite = it.isFavorite
                )
            }

    fun mapEntityToDomain(input: TvShowEntity): TvShow {
        val listGenre = ArrayList<Genre>()
        if (input.genres != null && input.genres?.isNotEmpty()!!) {
            listGenre.addAll(setUpGenre(input.genres!!))
        }
        return TvShow(
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
            numberOfSeasons = input.numberOfSeasons,
            isFavorite = input.isFavorite
        )
    }

    fun mapDomainToEntity(input: TvShow): TvShowEntity {
        var listGenre = ""
        if (input.genres != null && input.genres!!.isNotEmpty()) {
            listGenre = Gson().toJson(input.genres)
        }
        return TvShowEntity(
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
            numberOfSeasons = input.numberOfSeasons,
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