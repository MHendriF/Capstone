package com.mhendrif.capstone.core.di

import androidx.room.Room
import com.mhendrif.capstone.core.data.repository.MovieRepository
import com.mhendrif.capstone.core.data.repository.TvShowRepository
import com.mhendrif.capstone.core.data.source.local.LocalDataSource
import com.mhendrif.capstone.core.data.source.local.room.AppDatabase
import com.mhendrif.capstone.core.data.source.remote.RemoteDataSource
import com.mhendrif.capstone.core.data.source.remote.network.ApiService
import com.mhendrif.capstone.core.domain.repository.IMovieRepository
import com.mhendrif.capstone.core.domain.repository.ITvShowRepository
import com.mhendrif.capstone.core.utils.AppExecutors
import com.mhendrif.capstone.core.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<AppDatabase>().movieDao() }
    factory { get<AppDatabase>().tvShowDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, Constants.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.TMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get(), get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> { MovieRepository(get(), get(), get()) }
    single<ITvShowRepository> { TvShowRepository(get(), get(), get()) }
}