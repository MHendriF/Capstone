package com.mhendrif.capstone.core.domain.usecase

import com.mhendrif.capstone.core.domain.model.Movie
import com.mhendrif.capstone.core.domain.repository.IMovieRepository
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getAllMovie() = movieRepository.getAllMovie()

    override fun getFavorite() = movieRepository.getFavorite()

    override fun setFavorite(movie: Movie, state: Boolean) = movieRepository.setFavorite(movie, state)

    override fun getDetailMovie(id: String) = movieRepository.getDetailMovie(id)
}