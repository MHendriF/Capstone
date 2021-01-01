package com.mhendrif.capstone.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mhendrif.capstone.core.domain.model.Movie
import com.mhendrif.capstone.core.domain.model.TvShow
import com.mhendrif.capstone.core.domain.usecase.MovieUseCase
import com.mhendrif.capstone.core.domain.usecase.TvShowUseCase

class FavoriteViewModel @ViewModelInject constructor(
    movieUseCase: MovieUseCase,
    tvShowUseCase: TvShowUseCase
) : ViewModel() {
    val movies: LiveData<List<Movie>> = movieUseCase.getFavorite().asLiveData()
    val tvShows: LiveData<List<TvShow>> = tvShowUseCase.getFavorite().asLiveData()
}