package com.mhendrif.capstone.core.domain.usecase

import com.mhendrif.capstone.core.data.source.local.SortOrder
import com.mhendrif.capstone.core.domain.model.TvShow
import com.mhendrif.capstone.core.domain.repository.ITvShowRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvShowInteractor @Inject constructor(private val tvShowRepository: ITvShowRepository): TvShowUseCase {
    override fun getAllTvShow() = tvShowRepository.getAllTvShow()

    override fun getFavorite() = tvShowRepository.getFavorite()

    override fun setFavorite(tvShow: TvShow, state: Boolean) = tvShowRepository.setFavorite(tvShow, state)

    override fun getDetailTvShow(id: Int) = tvShowRepository.getDetailTvShow(id)

    override fun onSortOrderSelected(sortOrder: SortOrder) = tvShowRepository.onSortOrderSelected(sortOrder)
}