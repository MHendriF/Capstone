package com.mhendrif.capstone.data.source.remote.response

import android.os.Parcelable
import com.mhendrif.capstone.domain.model.Genre
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowResponse(
    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val title: String,

    @Json(name = "overview")
    val overview: String,

    @Json(name = "poster_path")
    val posterPath: String?,

    @Json(name = "backdrop_path")
    val backdropPath: String?,

    @Json(name = "first_air_date")
    val releaseDate: String?,

    @Json(name = "vote_average")
    val voteAverage: Double,

    @Json(name = "vote_count")
    val voteCount: Int,

    @Json(name = "genres")
    val genres: List<Genre>?,

    @Json(name = "homepage")
    val homepage: String?,

    @Json(name = "number_of_seasons")
    val numberOfSeasons: Int?,
) : Parcelable
