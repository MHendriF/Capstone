package com.mhendrif.capstone.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
class ListTvShowResponse(
    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("total_results")
    val totalResults: Int,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("results")
    val results: List<TvShowResponse>
) : Parcelable