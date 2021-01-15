package com.mhendrif.capstone.ui.detail

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mhendrif.capstone.domain.Resource
import com.mhendrif.capstone.domain.model.Movie
import com.mhendrif.capstone.domain.model.TvShow
import com.mhendrif.capstone.domain.usecase.MovieUseCase
import com.mhendrif.capstone.domain.usecase.TvShowUseCase
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val tvShowUseCase: TvShowUseCase
) : ViewModel() {

    val movie by lazy { MediatorLiveData<Resource<Movie>>() }
    val tvShow by lazy { MediatorLiveData<Resource<TvShow>>() }

    fun getDetailMovie(id: Int) {
        movie.addSource(movieUseCase.getDetailMovie(id).asLiveData()) { movie.value = it }
    }

    fun getDetailTvShow(id: Int) {
        tvShow.addSource(tvShowUseCase.getDetailTvShow(id).asLiveData()) { tvShow.value = it }
    }

    fun setFavoriteMovie(movie: Movie, isFavorite: Boolean) =
        movieUseCase.setFavorite(movie, isFavorite)

    fun setFavoriteTvShow(tvShow: TvShow, isFavorite: Boolean) =
        tvShowUseCase.setFavorite(tvShow, isFavorite)
}
