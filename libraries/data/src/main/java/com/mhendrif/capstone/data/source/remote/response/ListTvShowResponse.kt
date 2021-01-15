package com.mhendrif.capstone.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class ListTvShowResponse(
    @field:SerializedName("results")
    val results: List<TvShowResponse>
) : Parcelable
