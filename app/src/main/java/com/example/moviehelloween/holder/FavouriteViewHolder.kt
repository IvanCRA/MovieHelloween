package com.example.moviehelloween.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviehelloween.Favourite
import com.example.moviehelloween.Movie
import com.example.moviehelloween.R
import com.google.android.material.imageview.ShapeableImageView

class FavouriteViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
) {
    val textItemName = itemView.findViewById<TextView>(R.id.text_item_name)
    val textItemYear = itemView.findViewById<TextView>(R.id.text_item_year)
    val filmRatingBackground =
        itemView.findViewById<ShapeableImageView>(R.id.film_rating_background)
    val imageViewItem = itemView.findViewById<ImageView>(R.id.image_view_item)
    val textRatingInt = itemView.findViewById<TextView>(R.id.text_rating_int)

    private val set = ConstraintSet()
    private val requestOptions = RequestOptions().placeholder(R.drawable.outline_image_24)
    fun bind(favourite: Favourite) {
        textItemName.text = favourite.title
        textItemYear.text = favourite.year.toString()
        if (favourite.rating!! >= 7.0) {
            filmRatingBackground.setBackgroundResource(R.color.rating_green)
        } else {
            filmRatingBackground.setBackgroundResource(R.color.rating_gray)
        }

        textRatingInt.text =
            if (favourite.rating.toString() == "10.0") "10" else favourite.rating.toString()

        Glide.with(itemView.context)
            .setDefaultRequestOptions(requestOptions)
            .load(favourite.imgUrl)
            .into(imageViewItem)
    }
}