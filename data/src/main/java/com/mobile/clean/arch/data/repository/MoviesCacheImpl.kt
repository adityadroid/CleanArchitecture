package com.mobile.clean.arch.data.repository

import com.mobile.clean.arch.data.db.MoviesDao
import com.mobile.clean.arch.data.db.MoviesDatabase
import com.mobile.clean.arch.data.entities.MoviesDataEntityMapper
import com.mobile.clean.arch.data.entities.MoviesEntityDataMapper
import com.mobile.clean.arch.domain.entities.MoviesEntity
import io.reactivex.Flowable

class MoviesCacheImpl(
    private val database: MoviesDatabase,
    private val entityToDataMapper: MoviesEntityDataMapper,
    private val dataToEntityMapper: MoviesDataEntityMapper
) : MoviesDataStore {

    private val moviesDao: MoviesDao = database.getMoviesDao()

    override fun getMovies(
        mediaType: String,
        timeWindow: String
    ): Flowable<MoviesEntity> {
        return moviesDao.getMovies().map {
            dataToEntityMapper.mapToEntity(it)
        }
    }

    fun saveMovies(moviesEntity: MoviesEntity) {
        moviesDao.clear()
        moviesDao.saveAllMovies(moviesEntity.results.map { movies ->
            entityToDataMapper.mapMoviesToEntity(
                movies
            )
        })
    }

}