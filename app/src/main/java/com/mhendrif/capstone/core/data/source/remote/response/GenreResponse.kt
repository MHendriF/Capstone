package com.mhendrif.capstone.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenreResponse(
    @field:SerializedName("id")
    val genre_code: Int,

    @field:SerializedName("name")
    val name: String
) : Parcelable
