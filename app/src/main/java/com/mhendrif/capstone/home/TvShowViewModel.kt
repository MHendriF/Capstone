package com.mhendrif.capstone.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mhendrif.capstone.domain.usecase.TvShowUseCase
import javax.inject.Inject

class TvShowViewModel @Inject constructor(
    tvShowUseCase: TvShowUseCase
) : ViewModel() {
    val tvShow = tvShowUseCase.getAllTvShow().asLiveData()
}