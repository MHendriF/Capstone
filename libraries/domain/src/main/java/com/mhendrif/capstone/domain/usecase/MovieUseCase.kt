package com.mhendrif.capstone.domain.usecase

import com.mhendrif.capstone.common.util.SortOrder
import com.mhendrif.capstone.domain.Resource
import com.mhendrif.capstone.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
    fun getFavorite(): Flow<List<Movie>>
    fun setFavorite(movie: Movie, state: Boolean)
    fun getDetailMovie(id: Int): Flow<Resource<Movie>>
    fun getFavoriteBySort(sortOrder: SortOrder): Flow<List<Movie>>
}
