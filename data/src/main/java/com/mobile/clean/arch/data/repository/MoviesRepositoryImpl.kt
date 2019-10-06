package com.mobile.clean.arch.data.repository

import com.mobile.clean.arch.domain.entities.MoviesEntity
import com.mobile.clean.arch.domain.repositories.MoviesRepository
import io.reactivex.Flowable

class MoviesRepositoryImpl(
    private val remote: MoviesRemoteImpl,
    private val cache: MoviesCacheImpl
) : MoviesRepository {

    override fun getMovies(
        mediaType: String,
        timeWindow: String
    ): Flowable<MoviesEntity> {
        val updateMoviesFlowable = remote.getMovies(mediaType, timeWindow)
        return cache.getMovies(mediaType, timeWindow)
            .mergeWith(updateMoviesFlowable.doOnNext { remoteMovies ->
                cache.saveMovies(remoteMovies)
            })
    }

    override fun getLocalMovies(
        mediaType: String,
        timeWindow: String
    ): Flowable<MoviesEntity> = cache.getMovies(mediaType, timeWindow)

    override fun getRemoteMovies(
        mediaType: String,
        timeWindow: String
    ): Flowable<MoviesEntity> = remote.getMovies(mediaType, timeWindow)

}