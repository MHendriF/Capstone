package com.mhendrif.capstone.data.source.remote.response

import android.os.Parcelable
import com.mhendrif.capstone.domain.model.Genre
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
    @Json(name = "id")
    val id: Int,

    @Json(name = "title")
    val title: String,

    @Json(name = "overview")
    val overview: String,

    @Json(name = "poster_path")
    val posterPath: String?,

    @Json(name = "backdrop_path")
    val backdropPath: String?,

    @Json(name = "release_date")
    val releaseDate: String?,

    @Json(name = "vote_average")
    val voteAverage: Double,

    @Json(name = "vote_count")
    val voteCount: Int,

    @Json(name = "genres")
    val genres: List<Genre>?,

    @Json(name = "homepage")
    val homepage: String?,

    @Json(name = "runtime")
    val runtime: String?
) : Parcelable
