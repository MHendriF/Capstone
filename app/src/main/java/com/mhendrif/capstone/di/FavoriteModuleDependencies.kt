package com.mhendrif.capstone.di

import com.mhendrif.capstone.domain.usecase.MovieUseCase
import com.mhendrif.capstone.domain.usecase.TvShowUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface FavoriteModuleDependencies {

    fun movieUseCase(): MovieUseCase

    fun tvShowUseCase(): TvShowUseCase
}
