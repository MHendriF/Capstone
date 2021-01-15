package com.mhendrif.capstone.di

import com.mhendrif.capstone.MainActivity
import com.mhendrif.capstone.SplashActivity
import com.mhendrif.capstone.core.di.CoreComponent
import com.mhendrif.capstone.ui.detail.DetailMovieFragment
import com.mhendrif.capstone.ui.detail.DetailTvShowFragment
import com.mhendrif.capstone.ui.home.MovieFragment
import com.mhendrif.capstone.ui.home.TvShowFragment
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

    fun inject(activity: SplashActivity)
    fun inject(activity: MainActivity)
    fun inject(fragment: MovieFragment)
    fun inject(fragment: TvShowFragment)
    fun inject(fragment: DetailMovieFragment)
    fun inject(fragment: DetailTvShowFragment)
}
