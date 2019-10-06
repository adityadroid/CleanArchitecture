package com.mobile.clean.arch.data.repository

import com.mobile.clean.arch.data.api.RemoteMoviesApi
import com.mobile.clean.arch.data.entities.MoviesDataEntityMapper
import com.mobile.clean.arch.domain.entities.MoviesEntity
import io.reactivex.Flowable

class MoviesRemoteImpl constructor(private val api: RemoteMoviesApi) : MoviesDataStore {

    private val moviesMapper = MoviesDataEntityMapper()

    override fun getMovies(
        mediaType: String,
        timeWindow: String
    ): Flowable<MoviesEntity> {
        return api.getMovieList(mediaType, timeWindow).map { moviesMapper.mapToEntity(it) }
    }

}
