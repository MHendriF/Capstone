package com.mhendrif.capstone.favorite.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mhendrif.capstone.domain.usecase.MovieUseCase
import com.mhendrif.capstone.domain.usecase.TvShowUseCase
import com.mhendrif.capstone.favorite.ui.FavoriteViewModel
import javax.inject.Inject

class FavoriteViewModelFactory @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val tvShowUseCase: TvShowUseCase,
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(movieUseCase, tvShowUseCase) as T
            }
            else -> throw IllegalArgumentException("unknown model class ${modelClass.name}")
        }
}
