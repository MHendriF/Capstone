package com.mhendrif.capstone.di

import com.mhendrif.capstone.MainActivity
import com.mhendrif.capstone.core.di.CoreComponent
import com.mhendrif.capstone.detail.DetailActivity
import com.mhendrif.capstone.detail.DetailMovieFragment
import com.mhendrif.capstone.detail.DetailTvShowFragment
import com.mhendrif.capstone.favorite.FavoriteFragment
import com.mhendrif.capstone.favorite.FavoriteMovieFragment
import com.mhendrif.capstone.favorite.FavoriteTvShowFragment
import com.mhendrif.capstone.home.MovieFragment
import com.mhendrif.capstone.home.TvShowFragment
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, ViewModelModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(activity: DetailActivity)
    fun inject(fragment: MovieFragment)
    fun inject(fragment: TvShowFragment)
    fun inject(fragment: FavoriteFragment)
    fun inject(fragment: FavoriteMovieFragment)
    fun inject(fragment: FavoriteTvShowFragment)
    fun inject(fragment: DetailMovieFragment)
    fun inject(fragment: DetailTvShowFragment)
}