package com.mhendrif.capstone.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mhendrif.capstone.core.data.Resource
import com.mhendrif.capstone.core.domain.model.Movie
import com.mhendrif.capstone.core.domain.model.TvShow
import com.mhendrif.capstone.core.domain.usecase.MovieUseCase
import com.mhendrif.capstone.core.domain.usecase.TvShowUseCase

class DetailViewModel @ViewModelInject constructor(
    private val movieUseCase: MovieUseCase,
    private val tvShowUseCase: TvShowUseCase
) : ViewModel() {

    var movie: LiveData<Resource<Movie>> = MutableLiveData()
    var tvShow: LiveData<Resource<TvShow>> = MutableLiveData()
    private lateinit var dataExtra: MutableList<Int>

    fun init(dataDes: Int, dataId: Int) {
        dataExtra = mutableListOf(dataDes, dataId)
    }

    fun getDetailMovie(id: Int) {
        movie = movieUseCase.getDetailMovie(id).asLiveData()
    }

    fun getDetailTvShow(id: Int) {
        tvShow = tvShowUseCase.getDetailTvShow(id).asLiveData()
    }

    fun getExtra(data: Int) = this.dataExtra[data]

    fun setFavoriteMovie(movie: Movie, isFavorite: Boolean) =
        movieUseCase.setFavorite(movie, isFavorite)

    fun setFavoriteTvShow(tvShow: TvShow, isFavorite: Boolean) =
        tvShowUseCase.setFavorite(tvShow, isFavorite)

}