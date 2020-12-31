package com.mhendrif.capstone.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.mhendrif.capstone.core.domain.model.Genre
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Int,

    @SerializedName("genres")
    val genres: List<Genre>? = null,

    @SerializedName("homepage")
    val homepage: String? = null
) : Parcelable