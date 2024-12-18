package com.example.moviehelloween.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviehelloween.Movie
import com.example.moviehelloween.holder.HomeViewHolder

class HomeAdapter(private val context: Context, private val movieList: List<Movie>) : RecyclerView.Adapter<HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(parent)
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)
    }
}