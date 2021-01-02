package com.mhendrif.capstone.core.domain.repository

import com.mhendrif.capstone.core.data.Resource
import com.mhendrif.capstone.core.data.source.local.SortOrder
import com.mhendrif.capstone.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
    fun getFavorite(): Flow<List<Movie>>
    fun setFavorite(movie: Movie, state: Boolean)
    fun getDetailMovie(id: Int): Flow<Resource<Movie>>
    fun onSortOrderSelected(sortOrder: SortOrder): Flow<List<Movie>>
}