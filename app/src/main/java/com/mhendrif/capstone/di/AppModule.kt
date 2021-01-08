package com.mhendrif.capstone.di

import com.mhendrif.capstone.domain.usecase.MovieInteractor
import com.mhendrif.capstone.domain.usecase.MovieUseCase
import com.mhendrif.capstone.domain.usecase.TvShowInteractor
import com.mhendrif.capstone.domain.usecase.TvShowUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): MovieUseCase

    @Binds
    abstract fun provideTvShowUseCase(tvShowInteractor: TvShowInteractor): TvShowUseCase
}