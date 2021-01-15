package com.mhendrif.capstone.core.di

import android.content.Context
import com.mhendrif.capstone.domain.repository.IMovieRepository
import com.mhendrif.capstone.domain.repository.ITvShowRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class]
)
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    fun provideRepository(): IMovieRepository
    fun provideTvShowRepository(): ITvShowRepository
}
