package com.mhendrif.capstone.core.di

import com.mhendrif.capstone.core.data.repository.MovieRepository
import com.mhendrif.capstone.core.data.repository.TvShowRepository
import com.mhendrif.capstone.core.domain.repository.IMovieRepository
import com.mhendrif.capstone.core.domain.repository.ITvShowRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideMovieRepository(movieRepository: MovieRepository): IMovieRepository

    @Binds
    abstract fun provideTvShowRepository(tvShowRepository: TvShowRepository): ITvShowRepository
}