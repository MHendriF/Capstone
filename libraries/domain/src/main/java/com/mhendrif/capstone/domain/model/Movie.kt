package com.mhendrif.capstone.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String?,
    val voteAverage: Double,
    val voteCount: Int,
    val genres: List<Genre>?,
    val homepage: String?,
    val runtime: String?,
    val isFavorite: Boolean
) : Parcelable
