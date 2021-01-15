package com.mhendrif.capstone.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mhendrif.capstone.domain.usecase.MovieUseCase
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    movieUseCase: MovieUseCase
) : ViewModel() {

    var movie = movieUseCase.getAllMovie().asLiveData()
}
