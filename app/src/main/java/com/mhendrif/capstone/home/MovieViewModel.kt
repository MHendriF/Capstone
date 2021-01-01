package com.mhendrif.capstone.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mhendrif.capstone.core.domain.usecase.MovieUseCase

class MovieViewModel @ViewModelInject constructor(
    movieUseCase: MovieUseCase
) : ViewModel() {
    val movie = movieUseCase.getAllMovie().asLiveData()
}