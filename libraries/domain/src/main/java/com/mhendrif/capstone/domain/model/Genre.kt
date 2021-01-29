package com.mhendrif.capstone.domain.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Genre(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String
) : Parcelable
