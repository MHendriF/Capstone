package com.mhendrif.capstone.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mhendrif.capstone.core.data.Resource
import com.mhendrif.capstone.core.data.source.local.SortOrder
import com.mhendrif.capstone.core.domain.model.Movie
import com.mhendrif.capstone.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.launch

class MovieViewModel @ViewModelInject constructor(
    movieUseCase: MovieUseCase
) : ViewModel() {
    var movie = movieUseCase.getAllMovie().asLiveData()
}