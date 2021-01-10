package com.mhendrif.capstone.favorite.ui

import androidx.lifecycle.*
import com.mhendrif.capstone.common.util.SortOrder
import com.mhendrif.capstone.domain.model.Movie
import com.mhendrif.capstone.domain.model.TvShow
import com.mhendrif.capstone.domain.usecase.MovieUseCase
import com.mhendrif.capstone.domain.usecase.TvShowUseCase
import timber.log.Timber
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val tvShowUseCase: TvShowUseCase,
) : ViewModel() {

    var movies: MediatorLiveData<List<Movie>> = MediatorLiveData()
    var tvShows: MediatorLiveData<List<TvShow>> = MediatorLiveData()

    fun getFavoriteMovies() {
        movies.addSource(movieUseCase.getFavoriteBySort(SortOrder.BY_NAME).asLiveData()) {movies.value = it}
    }

    fun getFavoriteTvShows() {
        tvShows.addSource(tvShowUseCase.getFavoriteBySort(SortOrder.BY_NAME).asLiveData()) {tvShows.value = it}
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
            }
            SortOrder.BY_DATE -> {
                movies.addSource(movieUseCase.getFavoriteBySort(SortOrder.BY_DATE).asLiveData()) {
                    movies.value = it
                }
                tvShows.addSource(tvShowUseCase.getFavoriteBySort(SortOrder.BY_DATE).asLiveData()) {
                    tvShows.value = it
                }
            }
        }
    }
}