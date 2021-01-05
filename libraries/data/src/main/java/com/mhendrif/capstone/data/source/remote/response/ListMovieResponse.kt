package com.mhendrif.capstone.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListMovieResponse(
    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("total_results")
    val totalResults: Int,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("results")
    val results: List<MovieResponse>
) : Parcelable