package com.mhendrif.capstone.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mhendrif.capstone.ViewModelFactory
import com.mhendrif.capstone.detail.DetailViewModel
import com.mhendrif.capstone.favorite.FavoriteViewModel
import com.mhendrif.capstone.home.MovieViewModel
import com.mhendrif.capstone.home.TvShowViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindMovieViewModel(viewModel: MovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TvShowViewModel::class)
    abstract fun bindTvShowViewModel(viewModel: TvShowViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun bindFavoriteViewModel(viewModel: FavoriteViewModel): ViewModel
}