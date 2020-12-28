package com.mhendrif.capstone.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    val genre_code: Int,
    val name: String
) : Parcelable
