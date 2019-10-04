package com.mobile.clean.arch.presentation.di

import androidx.room.Room
import com.mobile.clean.arch.data.api.RemoteMoviesApi
import com.mobile.clean.arch.data.db.MoviesDatabase
import com.mobile.clean.arch.data.entities.MoviesDataEntityMapper
import com.mobile.clean.arch.data.entities.MoviesEntityDataMapper
import com.mobile.clean.arch.data.repository.MoviesCacheImpl
import com.mobile.clean.arch.data.repository.MoviesRemoteImpl
import com.mobile.clean.arch.data.repository.MoviesRepositoryImpl
import com.mobile.clean.arch.domain.repositories.MoviesRepository
import com.mobile.clean.arch.domain.usecases.GetMoviesUseCase
import com.mobile.clean.arch.presentation.common.AsyncFlowableTransformer
import com.mobile.clean.arch.presentation.mappers.MoviesEntityMapper
import com.mobile.clean.arch.presentation.movies.MoviesViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit

val repositoryModules = module {
    single(name = "remote") {
        MoviesRemoteImpl(api = get(API))
    }
    single(name = "local") {
        MoviesCacheImpl(
            database = get(DATABASE),
            entityToDataMapper = MoviesEntityDataMapper(),
            dataToEntityMapper = MoviesDataEntityMapper()
        )
    }
    single {
        MoviesRepositoryImpl(
            remote = get("remote"),
            cache = get("local")
        ) as MoviesRepository
    }
}

val useCaseModules = module {
    factory(name = "getMoviesUseCase") {
        GetMoviesUseCase(transformer = AsyncFlowableTransformer(), repositories = get())
    }
}

val networkModules = module {
    single(name = RETROFIT_INSTANCE) {
        createNetworkClient(BASE_URL)
    }
    single(name = API) {
        (get(RETROFIT_INSTANCE) as Retrofit).create(RemoteMoviesApi::class.java)
    }
}

val localModules = module {
    single(name = DATABASE) {
        Room.databaseBuilder(androidApplication(), MoviesDatabase::class.java, "movies").build()
    }
}

val viewModelModules = module {
    viewModel {
        MoviesViewModel(getMoviesUseCase = get(GET_MOVIES_USECASE), mapper = MoviesEntityMapper())
    }
}


private const val BASE_URL = "https://api.themoviedb.org/3"
private const val RETROFIT_INSTANCE = "Retrofit"
private const val API = "Api"
private const val GET_MOVIES_USECASE = "getMoviesUseCase"
private const val REMOTE = "remote response"
private const val DATABASE = "database"