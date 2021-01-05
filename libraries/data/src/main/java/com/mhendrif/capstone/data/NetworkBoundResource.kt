package com.mhendrif.capstone.data

import com.mhendrif.capstone.data.source.remote.network.ApiResponse
import com.mhendrif.capstone.data.util.AppExecutors
import com.mhendrif.capstone.domain.Resource
import kotlinx.coroutines.flow.*
import timber.log.Timber

abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutors: AppExecutors) {
    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    Timber.d("Timber - success")
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map {
                       Resource.Success(
                            it
                        )
                    })
                }
                is ApiResponse.Empty -> {
                    Timber.d("Timber - empty")
                    emitAll(loadFromDB().map {
                        Resource.Success(
                            it
                        )
                    })
                }
                is ApiResponse.Error -> {
                    Timber.d("Timber - error")
                    onFetchFailed()
                    emit(
                        Resource.Error<ResultType>(
                            apiResponse.errorMessage
                        )
                    )
                }
            }
        } else {
            emitAll(loadFromDB().map {
                Resource.Success(
                    it
                )
            })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}