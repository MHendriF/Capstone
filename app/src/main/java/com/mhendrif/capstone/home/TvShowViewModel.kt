package com.mhendrif.capstone.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mhendrif.capstone.core.domain.usecase.TvShowUseCase

class TvShowViewModel(tvShowUseCase: TvShowUseCase) : ViewModel() {
    val tvShow = tvShowUseCase.getAllTvShow().asLiveData()
}