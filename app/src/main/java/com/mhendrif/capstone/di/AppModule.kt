package com.mhendrif.capstone.di

import com.mhendrif.capstone.core.domain.usecase.MovieInteractor
import com.mhendrif.capstone.core.domain.usecase.MovieUseCase
import com.mhendrif.capstone.core.domain.usecase.TvShowInteractor
import com.mhendrif.capstone.core.domain.usecase.TvShowUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): MovieUseCase

    @Binds
    abstract fun provideTvShowUseCase(tvShowInteractor: TvShowInteractor): TvShowUseCase
}