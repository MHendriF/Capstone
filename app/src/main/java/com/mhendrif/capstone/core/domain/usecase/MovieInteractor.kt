package com.mhendrif.capstone.core.domain.usecase

import com.mhendrif.capstone.core.domain.model.Movie
import com.mhendrif.capstone.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getAllMovie() = movieRepository.getAllMovie()

    override fun getFavorite() = movieRepository.getFavorite()

    override fun setFavorite(movie: Movie, state: Boolean) = movieRepository.setFavorite(movie, state)

    override fun getDetailMovie(id: Int) = movieRepository.getDetailMovie(id)

    override suspend fun getDetailMovie2(id: Int): Flow<Movie> = movieRepository.getDetailMovie2(id)

    override fun getDetailFavorite(id: Int): Flow<Movie> {
        TODO("Not yet implemented")
    }
}