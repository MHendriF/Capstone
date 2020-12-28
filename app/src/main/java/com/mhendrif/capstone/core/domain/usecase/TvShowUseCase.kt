package com.mhendrif.capstone.core.domain.usecase

import com.mhendrif.capstone.core.data.Resource
import com.mhendrif.capstone.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowUseCase {
    fun getAllMovie(): Flow<Resource<List<TvShow>>>
    fun getFavorite(): Flow<List<TvShow>>
    fun setFavorite(tvShow: TvShow, state: Boolean)
}