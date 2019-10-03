package com.mobile.clean.arch.domain.usecases

import com.mobile.clean.arch.domain.common.BaseFlowableUseCase
import com.mobile.clean.arch.domain.common.FlowableRxTransformer
import com.mobile.clean.arch.domain.entities.MoviesEntity
import com.mobile.clean.arch.domain.repositories.MoviesRepository
import io.reactivex.Flowable

class GetMoviesUseCase(
    private val transformer: FlowableRxTransformer<MoviesEntity>,
    private val repositories: MoviesRepository
) : BaseFlowableUseCase<MoviesEntity>(transformer) {

    override fun createFlowable(data: Map<String, Any>?): Flowable<MoviesEntity> {
        return repositories.getMovies()
    }

    fun getMovies(): Flowable<MoviesEntity> {
        val data = HashMap<String, String>()
        return single(data)
    }
}