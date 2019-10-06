package com.mobile.clean.arch.presentation.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.clean.arch.presentation.R
import com.mobile.clean.arch.presentation.di.IMAGE_URL
import com.mobile.clean.arch.presentation.entities.MoviesItem
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movies_item.view.*

class MoviesListAdapter : RecyclerView.Adapter<MoviesListAdapter.MoviesViewHolder>() {

    var movies = mutableListOf<MoviesItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(moviesItem: MoviesItem) {
            with(itemView) {
                textViewTitle.text = moviesItem.title
                Picasso.with(itemView.context).load(IMAGE_URL + moviesItem.poster_path)
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(imageViewPoster)
            }
        }
    }

    fun updateList(list: List<MoviesItem>) {
        if (list.isNotEmpty()) {
            movies.clear()
            movies.addAll(list)
            notifyDataSetChanged()
        }
    }
}