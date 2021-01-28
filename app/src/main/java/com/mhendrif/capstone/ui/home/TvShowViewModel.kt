package com.mhendrif.capstone.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mhendrif.capstone.domain.usecase.TvShowUseCase

class TvShowViewModel @ViewModelInject constructor(
    tvShowUseCase: TvShowUseCase
) : ViewModel() {

    val tvShow = tvShowUseCase.getAllTvShow().asLiveData()
}
