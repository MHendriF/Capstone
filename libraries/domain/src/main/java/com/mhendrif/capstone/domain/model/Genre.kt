package com.mhendrif.capstone.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String
) : Parcelable
