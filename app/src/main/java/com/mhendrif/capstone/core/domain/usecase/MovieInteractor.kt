package com.mhendrif.capstone.core.domain.usecase

import com.mhendrif.capstone.core.data.repository.MovieRepository
import com.mhendrif.capstone.core.domain.model.Movie

class MovieInteractor(private val movieRepository: MovieRepository) : MovieUseCase {
    override fun getAllMovie() = movieRepository.getAllMovie()

    override fun getFavorite() = movieRepository.getFavorite()

    override fun setFavorite(movie: Movie, state: Boolean) = movieRepository.setFavorite(movie, state)

    override fun getDetailMovie(id: String) = movieRepository.getDetailMovie(id)
}