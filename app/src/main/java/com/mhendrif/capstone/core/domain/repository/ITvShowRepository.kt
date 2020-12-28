package com.mhendrif.capstone.core.domain.repository

import com.mhendrif.capstone.core.data.Resource
import com.mhendrif.capstone.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface ITvShowRepository {
    fun getAllMovie(): Flow<Resource<List<TvShow>>>
    fun getFavorite(): Flow<List<TvShow>>
    fun setFavorite(tvShow: TvShow, state: Boolean)
}