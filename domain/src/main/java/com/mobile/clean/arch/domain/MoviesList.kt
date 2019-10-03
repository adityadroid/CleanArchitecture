package com.mobile.clean.arch.domain

data class MoviesList(
    var page: Int,
    var results: List<MoviesItem> = emptyList()
)