package com.mobile.clean.arch.domain.usecases

import com.mobile.clean.arch.domain.common.BaseFlowableUseCase
import com.mobile.clean.arch.domain.common.FlowableRxTransformer
import com.mobile.clean.arch.domain.entities.MoviesEntity
import com.mobile.clean.arch.domain.repositories.MoviesRepository
import io.reactivex.Flowable

class GetRemoteMoviesUseCase(
    private val transformer: FlowableRxTransformer<MoviesEntity>,
    private val repositories: MoviesRepository
) : BaseFlowableUseCase<MoviesEntity>(transformer) {


    fun getMovies(
        mediaType: String,
        timeWindow: String,
        apiKey: String
    ): Flowable<MoviesEntity> {
        return single(mediaType, timeWindow, apiKey)
    }

    override fun createFlowable(
        mediaType: String,
        timeWindow: String,
        apiKey: String
    ): Flowable<MoviesEntity> {
        return repositories.getMovies(mediaType, timeWindow, apiKey)
    }


    companion object {
        private const val PARAM_FILE_MOVIES_ENTITY = "param:MoviesStatus"
    }
}