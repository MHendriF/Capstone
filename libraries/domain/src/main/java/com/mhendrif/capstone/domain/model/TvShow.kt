package com.mhendrif.capstone.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShow(
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
    val numberOfSeasons: Int?,
    val isFavorite: Boolean
) : Parcelable
