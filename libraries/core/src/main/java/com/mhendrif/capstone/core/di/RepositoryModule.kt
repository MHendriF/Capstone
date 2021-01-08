package com.mhendrif.capstone.core.di

import com.mhendrif.capstone.data.repository.MovieRepository
import com.mhendrif.capstone.data.repository.TvShowRepository
import com.mhendrif.capstone.domain.repository.IMovieRepository
import com.mhendrif.capstone.domain.repository.ITvShowRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {
    @Binds
    abstract fun provideMovieRepository(movieRepository: MovieRepository): IMovieRepository

    @Binds
    abstract fun provideTvShowRepository(tvShowRepository: TvShowRepository): ITvShowRepository
}