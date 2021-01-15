package com.mhendrif.capstone.data.repository

import com.mhendrif.capstone.common.util.SortOrder
import com.mhendrif.capstone.data.NetworkBoundResource
import com.mhendrif.capstone.data.mapper.TvShowDataMapper
import com.mhendrif.capstone.data.source.local.LocalDataSource
import com.mhendrif.capstone.data.source.remote.RemoteDataSource
import com.mhendrif.capstone.data.source.remote.network.ApiResponse
import com.mhendrif.capstone.data.source.remote.response.TvShowResponse
import com.mhendrif.capstone.data.util.AppExecutors
import com.mhendrif.capstone.domain.Resource
import com.mhendrif.capstone.domain.model.TvShow
import com.mhendrif.capstone.domain.repository.ITvShowRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Singleton
class TvShowRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ITvShowRepository {

    override fun getAllTvShow(): Flow<Resource<List<TvShow>>> =
        object : NetworkBoundResource<List<TvShow>, List<TvShowResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<TvShow>> {
                return localDataSource.getAllTvShow().map {
                    TvShowDataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getAllTvShow()

            override suspend fun saveCallResult(data: List<TvShowResponse>) {
                val dataList = TvShowDataMapper.mapResponsesToEntities(data)
                localDataSource.insertAllTvShow(dataList)
            }
        }.asFlow()

    override fun getFavorite(): Flow<List<TvShow>> {
        return localDataSource.getFavoriteTvShow().map {
            TvShowDataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavorite(tvShow: TvShow, state: Boolean) {
        val entity = TvShowDataMapper.mapDomainToEntity(tvShow)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTvShow(entity, state) }
    }

    override fun getDetailTvShow(id: Int): Flow<Resource<TvShow>> =
        object : NetworkBoundResource<TvShow, TvShowResponse>(appExecutors) {
            override fun loadFromDB(): Flow<TvShow> {
                return localDataSource.getDetailTvShow(id).map {
                    TvShowDataMapper.mapEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: TvShow?): Boolean =
                data?.genres == null || data.genres!!.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<TvShowResponse>> =
                remoteDataSource.getDetailTvShow(id)

            override suspend fun saveCallResult(data: TvShowResponse) {
                val entity = TvShowDataMapper.mapResponseToEntity(data)
                localDataSource.updateTvShow(entity)
            }
        }.asFlow()

    override fun getFavoriteBySort(sortOrder: SortOrder): Flow<List<TvShow>> {
        return localDataSource.getTvShowsOnSortOrderSelected(sortOrder).map {
            TvShowDataMapper.mapEntitiesToDomain(it)
        }
    }
}
