package com.mobile.clean.arch.presentation.movies

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mobile.clean.arch.domain.common.Mapper
import com.mobile.clean.arch.domain.entities.MoviesEntity
import com.mobile.clean.arch.domain.usecases.GetMoviesUseCase
import com.mobile.clean.arch.presentation.common.BaseViewModel
import com.mobile.clean.arch.presentation.entities.Data
import com.mobile.clean.arch.presentation.entities.Error
import com.mobile.clean.arch.presentation.entities.Movies
import com.mobile.clean.arch.presentation.entities.Status

class MoviesViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val mapper: Mapper<MoviesEntity, Movies>
) : BaseViewModel() {

    var mMovies = MutableLiveData<Data<Movies>>()

    fun fetchMovies(
        mediaType: String,
        timeWindow: String
    ) {
        val disposable = getMoviesUseCase.getMovies(mediaType, timeWindow)
            .flatMap { mapper.Flowable(it) }
            .subscribe({ response ->
                Log.d(TAG, "On next Called")
                mMovies.value = Data(
                    responseType = Status.SUCCESSFUL,
                    data = response
                )
            }, { error ->
                Log.d(TAG, "On error Called")
                mMovies.value = Data(
                    responseType = Status.ERROR,
                    error = Error(error.message)
                )
            }, {
                Log.d(TAG, "On complete Called")
            })
        addDisposable(disposable)
    }

    fun getMoviesLiveData() = mMovies

    companion object {
        private val TAG = "viewmodel"
    }
}
