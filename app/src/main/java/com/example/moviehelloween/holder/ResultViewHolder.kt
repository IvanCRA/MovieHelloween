package com.example.moviehelloween.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviehelloween.Movie
import com.example.moviehelloween.R
import com.google.android.material.imageview.ShapeableImageView

class ResultViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
) {

    val textItemName = itemView.findViewById<TextView>(R.id.text_item_name)
    val textItemYear = itemView.findViewById<TextView>(R.id.text_item_year)
    val filmRatingBackground =
        itemView.findViewById<ShapeableImageView>(R.id.film_rating_background)
    val imageViewItem = itemView.findViewById<ImageView>(R.id.image_view_item)
    val textRatingInt = itemView.findViewById<TextView>(R.id.text_rating_int)

    fun bind(movie: Movie) {
        textItemName.text = movie.movieShort?.title.toString()
        textRatingInt.text = movie.movieShort?.rating.toString()
        textItemYear.text = movie.movieShort?.year.toString()
        if (movie.movieShort?.rating!! >= 7.0) {
            filmRatingBackground.setBackgroundResource(R.color.rating_green)
        } else {
            filmRatingBackground.setBackgroundResource(R.color.rating_gray)
        }
        Glide.with(itemView)
            .load(movie.movieShort.imgUrl)
            .into(imageViewItem)
    }
}