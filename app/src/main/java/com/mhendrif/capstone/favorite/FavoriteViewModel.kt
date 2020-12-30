package com.mhendrif.capstone.favorite

import androidx.lifecycle.ViewModel
import com.mhendrif.capstone.core.domain.usecase.MovieUseCase
import com.mhendrif.capstone.core.domain.usecase.TvShowUseCase

class FavoriteViewModel(private val movieUseCase: MovieUseCase, tvShowUseCase: TvShowUseCase): ViewModel() {
}