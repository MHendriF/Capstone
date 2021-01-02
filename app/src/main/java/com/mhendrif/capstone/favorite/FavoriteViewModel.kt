package com.mhendrif.capstone.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mhendrif.capstone.core.domain.model.Movie
import com.mhendrif.capstone.core.domain.model.TvShow
import com.mhendrif.capstone.core.domain.usecase.MovieUseCase
import com.mhendrif.capstone.core.domain.usecase.TvShowUseCase
import com.mhendrif.capstone.core.utils.SortOrder
import timber.log.Timber

class FavoriteViewModel @ViewModelInject constructor(
    private val movieUseCase: MovieUseCase,
    private val tvShowUseCase: TvShowUseCase,
) : ViewModel() {

    var movies: MediatorLiveData<List<Movie>> = MediatorLiveData()
    var tvShows: MediatorLiveData<List<TvShow>> = MediatorLiveData()

    fun getFavoriteMovies() {
        movies.addSource(movieUseCase.getFavorite().asLiveData()) {movies.value = it}
    }

    fun getFavoriteTvShows() {
        tvShows.addSource(tvShowUseCase.getFavorite().asLiveData()) {tvShows.value = it}
    }

    fun sorting(sortOrder: SortOrder) {
        when (sortOrder) {
            SortOrder.BY_NAME -> {
                movies.addSource(movieUseCase.getFavoriteBySort(SortOrder.BY_NAME).asLiveData()) {
                    movies.value = it
                }
                tvShows.addSource(tvShowUseCase.getFavoriteBySort(SortOrder.BY_NAME).asLiveData()) {
                    tvShows.value = it
                }

                Timber.d("Timber sort by name")
            }
            SortOrder.BY_DATE -> {
                movies.addSource(movieUseCase.getFavoriteBySort(SortOrder.BY_DATE).asLiveData()) {
                    movies.value = it
                }
                tvShows.addSource(tvShowUseCase.getFavoriteBySort(SortOrder.BY_DATE).asLiveData()) {
                    tvShows.value = it
                }
                Timber.d("Timber sort by date")
            }
        }
    }
}