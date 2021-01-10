package com.mhendrif.capstone.favorite.di

import com.mhendrif.capstone.core.di.CoreComponent
import com.mhendrif.capstone.di.AppModule
import com.mhendrif.capstone.favorite.ui.FavoriteFragment
import com.mhendrif.capstone.favorite.ui.FavoriteMovieFragment
import com.mhendrif.capstone.favorite.ui.FavoriteTvShowFragment
import dagger.Component

@FavoriteAppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, FavoriteViewModelModule::class]
)
interface FavoriteComponent {

    fun inject(fragment: FavoriteFragment)
    fun inject(fragment: FavoriteMovieFragment)
    fun inject(fragment: FavoriteTvShowFragment)
}