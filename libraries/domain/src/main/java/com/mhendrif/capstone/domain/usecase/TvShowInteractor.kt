package com.mhendrif.capstone.domain.usecase

import com.mhendrif.capstone.common.util.SortOrder
import com.mhendrif.capstone.domain.model.TvShow
import com.mhendrif.capstone.domain.repository.ITvShowRepository
import javax.inject.Inject

class TvShowInteractor @Inject constructor(private val tvShowRepository: ITvShowRepository):
    TvShowUseCase {
    override fun getAllTvShow() = tvShowRepository.getAllTvShow()

    override fun getFavorite() = tvShowRepository.getFavorite()

    override fun setFavorite(tvShow: TvShow, state: Boolean) = tvShowRepository.setFavorite(tvShow, state)

    override fun getDetailTvShow(id: Int) = tvShowRepository.getDetailTvShow(id)

    override fun getFavoriteBySort(sortOrder: SortOrder) = tvShowRepository.getFavoriteBySort(sortOrder)
}