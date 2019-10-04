package com.mobile.clean.arch.presentation.di

import com.mobile.clean.arch.data.entities.MoviesDataEntityMapper
import com.mobile.clean.arch.data.entities.MoviesEntityDataMapper
import com.mobile.clean.arch.data.repository.MoviesCacheImpl
import com.mobile.clean.arch.data.repository.MoviesRemoteImpl
import org.koin.dsl.module.module

val repositoryModules = module {
    single(name = "remote") { MoviesRemoteImpl(api = get(API)) }
    single(name = "local") {
        MoviesCacheImpl(
            database = get(DATABASE),
            entityToDataMapper = MoviesEntityDataMapper(),
            dataToEntityMapper = MoviesDataEntityMapper()
        )
    }
}


private const val BASE_URL = "https://api.themoviedb.org/3"
private const val RETROFIT_INSTANCE = "Retrofit"
private const val API = "Api"
private const val GET_MOVIES_USECASE = "getMoviesUseCase"
private const val REMOTE = "remote response"
private const val DATABASE = "database"