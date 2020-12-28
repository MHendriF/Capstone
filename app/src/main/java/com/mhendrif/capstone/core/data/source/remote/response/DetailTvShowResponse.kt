package com.mhendrif.capstone.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailTvShowResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val title: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("poster_path")
    val poster_path: String,

    @field:SerializedName("backdrop_path")
    val backdrop_path: String,

    @field:SerializedName("release_date")
    val release_date: String,

    @field:SerializedName("vote_average")
    val vote_average: Double,

    @field:SerializedName("vote_count")
    val vote_count: Int,

    @field:SerializedName("genres")
    val genres: List<GenreResponse> = listOf(),

    @field:SerializedName("homepage")
    val homepage: String
) : Parcelable