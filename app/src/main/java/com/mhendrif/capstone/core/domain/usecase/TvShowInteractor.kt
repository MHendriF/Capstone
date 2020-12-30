package com.mhendrif.capstone.core.domain.usecase

import com.mhendrif.capstone.core.data.repository.TvShowRepository
import com.mhendrif.capstone.core.domain.model.TvShow

class TvShowInteractor(private val tvShowRepository: TvShowRepository): TvShowUseCase {
    override fun getAllTvShow() = tvShowRepository.getAllTvShow()

    override fun getFavorite() = tvShowRepository.getFavorite()

    override fun setFavorite(tvShow: TvShow, state: Boolean) = tvShowRepository.setFavorite(tvShow, state)

    override fun getDetailTvShow(id: String) = tvShowRepository.getDetailTvShow(id)
}