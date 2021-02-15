package com.mhendrif.capstone.data.source.remote.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListMovieResponse(
    @Json(name = "results")
    val results: List<MovieResponse>
) : Parcelable
