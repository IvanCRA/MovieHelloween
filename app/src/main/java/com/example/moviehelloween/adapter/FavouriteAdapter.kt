package com.example.moviehelloween.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviehelloween.Favourite
import com.example.moviehelloween.Movie
import com.example.moviehelloween.diffutils.FavouriteDiffCallback
import com.example.moviehelloween.diffutils.HomeDiffCallback
import com.example.moviehelloween.holder.FavouriteViewHolder
import com.example.moviehelloween.holder.HomeViewHolder

class FavouriteAdapter(private val context: Context, private var favouriteList: MutableList<Favourite>) : RecyclerView.Adapter<FavouriteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        return FavouriteViewHolder(parent)
    }

    override fun getItemCount() = favouriteList.size

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        val favourite = favouriteList[position]
        holder.bind(favourite)
    }

    fun updateFavourites(newFavourites: List<Favourite>) {
        val diffCallback = FavouriteDiffCallback(favouriteList, newFavourites)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        favouriteList.clear()
        favouriteList.addAll(newFavourites)
        diffResult.dispatchUpdatesTo(this)
    }
}