package com.mhendrif.capstone.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mhendrif.capstone.R
import com.mhendrif.capstone.core.data.Resource
import com.mhendrif.capstone.core.domain.model.Movie
import com.mhendrif.capstone.core.domain.model.TvShow
import com.mhendrif.capstone.core.domain.usecase.MovieUseCase
import com.mhendrif.capstone.core.domain.usecase.TvShowUseCase
import com.mhendrif.capstone.detail.DetailActivity.Companion.DATA_DESTINATION
import com.mhendrif.capstone.detail.DetailActivity.Companion.DATA_ID
import timber.log.Timber
import kotlin.properties.Delegates

class DetailViewModel @ViewModelInject constructor(
    private val movieUseCase: MovieUseCase,
    private val tvShowUseCase: TvShowUseCase
) : ViewModel() {

    var movie: LiveData<Resource<Movie>> = MutableLiveData()
    var tvShow: LiveData<Resource<TvShow>> = MutableLiveData()

    fun getDetailMovie(id: Int) {
        movie = movieUseCase.getDetailMovie(id).asLiveData()
    }

    fun getDetailTvShow(id: Int) {
        tvShow = tvShowUseCase.getDetailTvShow(id).asLiveData()
    }

    fun setFavoriteMovie(movie: Movie, isFavorite: Boolean) = movieUseCase.setFavorite(movie, isFavorite)

    fun setFavoriteTvShow(tvShow: TvShow, isFavorite: Boolean) = tvShowUseCase.setFavorite(tvShow, isFavorite)

}