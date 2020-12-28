package com.mhendrif.capstone.core.data.repository

import com.mhendrif.capstone.core.data.Resource
import com.mhendrif.capstone.core.data.source.local.LocalDataSource
import com.mhendrif.capstone.core.data.source.remote.RemoteDataSource
import com.mhendrif.capstone.core.domain.model.TvShow
import com.mhendrif.capstone.core.domain.repository.ITvShowRepository
import com.mhendrif.capstone.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvShowRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ITvShowRepository {
    override fun getAllMovie(): Flow<Resource<List<TvShow>>> {
        TODO("Not yet implemented")
    }

    override fun getFavorite(): Flow<List<TvShow>> {
        TODO("Not yet implemented")
    }

    override fun setFavorite(tvShow: TvShow, state: Boolean) {
        TODO("Not yet implemented")
    }
}