package com.mhendrif.capstone.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mhendrif.capstone.domain.usecase.MovieUseCase

class MovieViewModel @ViewModelInject constructor(
    movieUseCase: MovieUseCase
) : ViewModel() {

    var movie = movieUseCase.getAllMovie().asLiveData()
}
