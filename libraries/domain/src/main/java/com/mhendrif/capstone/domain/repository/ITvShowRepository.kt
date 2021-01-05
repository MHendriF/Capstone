package com.mhendrif.capstone.domain.repository

import com.mhendrif.capstone.common.util.SortOrder
import com.mhendrif.capstone.domain.Resource
import com.mhendrif.capstone.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface ITvShowRepository {
    fun getAllTvShow(): Flow<Resource<List<TvShow>>>
    fun getFavorite(): Flow<List<TvShow>>
    fun setFavorite(tvShow: TvShow, state: Boolean)
    fun getDetailTvShow(id: Int): Flow<Resource<TvShow>>
    fun getFavoriteBySort(sortOrder: SortOrder): Flow<List<TvShow>>
}