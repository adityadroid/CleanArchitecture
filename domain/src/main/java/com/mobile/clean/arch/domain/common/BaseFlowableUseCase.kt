package com.mobile.clean.arch.domain.common

import io.reactivex.Flowable

abstract class BaseFlowableUseCase<T>(private val transformer: FlowableRxTransformer<T>) {

    abstract fun createFlowable(
        mediaType: String,
        timeWindow: String
    ): Flowable<T>

    fun single(
        mediaType: String,
        timeWindow: String
    ): Flowable<T> {
        return createFlowable(mediaType, timeWindow).compose(transformer)
    }
}