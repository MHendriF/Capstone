package com.mhendrif.capstone.data.util

import com.mhendrif.capstone.domain.model.Genre
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

object GenreConverter {

    private val moshi = Moshi.Builder().build()

    fun toString(itemList: List<Genre>): String {
        val listType = Types.newParameterizedType(List::class.java, Genre::class.java)
        val adapter: JsonAdapter<List<Genre>> = moshi.adapter(listType)
        return adapter.toJson(itemList)
    }

    fun toListGenre(itemList: String): List<Genre>? {
        val listType = Types.newParameterizedType(List::class.java, Genre::class.java)
        val adapter: JsonAdapter<List<Genre>> = moshi.adapter(listType)
        return adapter.fromJson(itemList)
    }
}
