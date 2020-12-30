package com.mhendrif.capstone.di

import com.mhendrif.capstone.core.domain.usecase.MovieInteractor
import com.mhendrif.capstone.core.domain.usecase.MovieUseCase
import com.mhendrif.capstone.core.domain.usecase.TvShowInteractor
import com.mhendrif.capstone.core.domain.usecase.TvShowUseCase
import com.mhendrif.capstone.detail.DetailMovieViewModel
import com.mhendrif.capstone.detail.DetailTvShowViewModel
import com.mhendrif.capstone.favorite.FavoriteViewModel
import com.mhendrif.capstone.home.MovieViewModel
import com.mhendrif.capstone.home.TvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
    factory<TvShowUseCase> { TvShowInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { FavoriteViewModel(get(), get()) }
    viewModel { DetailMovieViewModel(get()) }
    viewModel { DetailTvShowViewModel(get()) }
}