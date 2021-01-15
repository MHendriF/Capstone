package com.mhendrif.capstone.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.mhendrif.capstone.domain.model.Genre
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val title: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("first_air_date")
    val releaseDate: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("vote_count")
    val voteCount: Int,

    @field:SerializedName("genres")
    val genres: List<Genre>? = null,

    @field:SerializedName("homepage")
    val homepage: String? = null,

    @field:SerializedName("number_of_seasons")
    val numberOfSeasons: Int? = 0,
) : Parcelable
