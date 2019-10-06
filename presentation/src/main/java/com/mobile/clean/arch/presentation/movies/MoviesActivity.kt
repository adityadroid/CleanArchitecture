package com.mobile.clean.arch.presentation.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.clean.arch.presentation.R
import com.mobile.clean.arch.presentation.entities.Status
import kotlinx.android.synthetic.main.activity_movies.*
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesActivity : AppCompatActivity() {

    private val moviesList: MoviesViewModel by viewModel()
    private lateinit var listAdapter: MoviesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        listAdapter = MoviesListAdapter()
        recyclerViewMovies.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerViewMovies.adapter = listAdapter
        moviesList.fetchMovies("all", "week")
    }

    override fun onStart() {
        super.onStart()
        moviesList.getMoviesLiveData().observe(this, Observer {
            when (it.responseType) {
                Status.ERROR -> {
                    //error handling
                }
                Status.LOADING -> {
                    //progress
                }
                Status.SUCCESSFUL -> {
                    //on successfull response
                }
            }
            it.data.let { response ->
                if (response != null) {
                    listAdapter.updateList(response.results)
                }
            }
        })
    }
}