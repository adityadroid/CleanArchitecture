package com.mobile.clean.arch.presentation

import android.app.Application
import com.mobile.clean.arch.presentation.di.*
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        startKoin(
            this, listOf(
                networkModules,
                viewModelModules,
                repositoryModules,
                useCaseModules,
                localModules
            )
        )
    }
}