package com.mobile.clean.arch.presentation.entities

data class Movies(
    var page: Int = 0,
    var results: List<MoviesItem> = emptyList()
)