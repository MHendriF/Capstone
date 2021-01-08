package com.mhendrif.capstone.detail

import androidx.lifecycle.*
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

//    private val _detailMovie = MutableLiveData<Movie>()
//    val detailMovie: LiveData<Movie> get() = _detailMovie
//
//    private val _detailTvShow = MutableLiveData<TvShow>()
//    val detailTvShow: LiveData<TvShow> get() = _detailTvShow

    var movie: LiveData<Resource<Movie>> = MutableLiveData()
    var tvShow: LiveData<Resource<TvShow>> = MutableLiveData()

    fun getDetailMovie(id: Int) {
        movie = movieUseCase.getDetailMovie(id).asLiveData()
    }

    fun getDetailTvShow(id: Int) {
        tvShow = tvShowUseCase.getDetailTvShow(id).asLiveData()
    }

    fun setFavoriteMovie(movie: Movie, isFavorite: Boolean) =
        movieUseCase.setFavorite(movie, isFavorite)

    fun setFavoriteTvShow(tvShow: TvShow, isFavorite: Boolean) =
        tvShowUseCase.setFavorite(tvShow, isFavorite)
}