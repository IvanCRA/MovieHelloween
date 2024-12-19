package com.example.moviehelloween.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviehelloween.Movie
import com.example.moviehelloween.diffutils.ResultDiffCallBack
import com.example.moviehelloween.fragment.ResultFragment
import com.example.moviehelloween.holder.ResultViewHolder

class ResultAdapter(private val context: ResultFragment, private val movieList: MutableList<Movie>) :
    RecyclerView.Adapter<ResultViewHolder>() {
    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    fun setItems(list: MutableList<Movie>) {
        val diffCallback = ResultDiffCallBack(movieList, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        movieList.clear()
        movieList.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }
}