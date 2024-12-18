package com.example.moviehelloween.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.example.moviehelloween.Favourite

class FavouriteDiffCallback(private val oldList: List<Favourite>, private val newList: List<Favourite>) :
    DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}