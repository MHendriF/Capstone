package com.mhendrif.capstone.favorite.di

import android.content.Context
import com.mhendrif.capstone.di.FavoriteModuleDependencies
import com.mhendrif.capstone.favorite.ui.FavoriteFragment
import com.mhendrif.capstone.favorite.ui.FavoriteMovieFragment
import com.mhendrif.capstone.favorite.ui.FavoriteTvShowFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {

    fun inject(fragment: FavoriteFragment)
    fun inject(fragment: FavoriteMovieFragment)
    fun inject(fragment: FavoriteTvShowFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteComponent
    }
}
