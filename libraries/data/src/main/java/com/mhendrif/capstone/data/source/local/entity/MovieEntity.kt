package com.mhendrif.capstone.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mhendrif.capstone.common.util.Constants

@Entity(tableName = Constants.MOVIE_TABLE_NAME)
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "poster_path")
    var posterPath: String?,

    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String?,

    @ColumnInfo(name = "release_date")
    var releaseDate: String?,

    @ColumnInfo(name = "vote_average")
    var voteAverage: Double,

    @ColumnInfo(name = "vote_count")
    var voteCount: Int,

    @ColumnInfo(name = "genres")
    var genres: String?,

    @ColumnInfo(name = "homepage")
    var homepage: String?,

    @ColumnInfo(name = "runtime")
    var runtime: String?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)