package com.mhendrif.capstone.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mhendrif.capstone.core.data.source.local.PreferenceManager
import com.mhendrif.capstone.core.data.source.local.SortOrder
import com.mhendrif.capstone.core.domain.model.Movie
import com.mhendrif.capstone.core.domain.model.TvShow
import com.mhendrif.capstone.core.domain.usecase.MovieUseCase
import com.mhendrif.capstone.core.domain.usecase.TvShowUseCase
import kotlinx.coroutines.launch
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
                movies.addSource(movieUseCase.onSortOrderSelected(SortOrder.BY_NAME).asLiveData()) {
                    movies.value = it
                }
                tvShows.addSource(tvShowUseCase.onSortOrderSelected(SortOrder.BY_NAME).asLiveData()) {
                    tvShows.value = it
                }

                Timber.d("Timber sort by name")
            }
            SortOrder.BY_DATE -> {
                movies.addSource(movieUseCase.onSortOrderSelected(SortOrder.BY_DATE).asLiveData()) {
                    movies.value = it
                }
                tvShows.addSource(tvShowUseCase.onSortOrderSelected(SortOrder.BY_DATE).asLiveData()) {
                    tvShows.value = it
                }
                Timber.d("Timber sort by date")
            }
        }
    }

//    fun onSortOrderSelected(sortOrder: SortOrder) = viewModelScope.launch {
//        preferencesManager.updateSortOrder(sortOrder)
//    }
}