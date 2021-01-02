package com.mhendrif.capstone.core.domain.usecase

import com.mhendrif.capstone.core.data.Resource
import com.mhendrif.capstone.core.domain.model.TvShow
import com.mhendrif.capstone.core.utils.SortOrder
import kotlinx.coroutines.flow.Flow

interface TvShowUseCase {
    fun getAllTvShow(): Flow<Resource<List<TvShow>>>
    fun getFavorite(): Flow<List<TvShow>>
    fun setFavorite(tvShow: TvShow, state: Boolean)
    fun getDetailTvShow(id: Int): Flow<Resource<TvShow>>
    fun getFavoriteBySort(sortOrder: SortOrder): Flow<List<TvShow>>
    //suspend fun updateSortOrder(sortOrder: SortOrder)
    //fun getSortOrder(): DataStore<Preferences>
}