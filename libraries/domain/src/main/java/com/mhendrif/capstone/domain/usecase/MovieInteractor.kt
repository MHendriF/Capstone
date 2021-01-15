package com.mhendrif.capstone.domain.usecase

import com.mhendrif.capstone.common.util.SortOrder
import com.mhendrif.capstone.domain.model.Movie
import com.mhendrif.capstone.domain.repository.IMovieRepository
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository) :
    MovieUseCase {
    override fun getAllMovie() = movieRepository.getAllMovie()

    override fun getFavorite() = movieRepository.getFavorite()

    override fun setFavorite(movie: Movie, state: Boolean) = movieRepository.setFavorite(movie, state)

    override fun getDetailMovie(id: Int) = movieRepository.getDetailMovie(id)

    override fun getFavoriteBySort(sortOrder: SortOrder) = movieRepository.getFavoriteBySort(sortOrder)
}
