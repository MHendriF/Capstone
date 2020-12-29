package com.mhendrif.capstone.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mhendrif.capstone.core.domain.usecase.TvShowUseCase

class TvShowViewModel @ViewModelInject constructor(tvShowUseCase: TvShowUseCase) : ViewModel() {
    val tvShow = tvShowUseCase.getAllTvShow().asLiveData()
}