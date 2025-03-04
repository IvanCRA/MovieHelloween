package com.example.moviehelloween.holder

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.example.moviehelloween.Movie
import com.example.moviehelloween.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.imageview.ShapeableImageView

class HomeViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
) {
    val textItemName = itemView.findViewById<TextView>(R.id.text_item_name)
    val textItemYear = itemView.findViewById<TextView>(R.id.text_item_year)
    val filmRatingBackground =
        itemView.findViewById<ShapeableImageView>(R.id.film_rating_background)
    val imageViewItem = itemView.findViewById<ImageView>(R.id.image_view_item)
    val textRatingInt = itemView.findViewById<TextView>(R.id.text_rating_int)
    val btnAddFavour = itemView.findViewById<ImageButton>(R.id.btn_add_favour)

    private val set = ConstraintSet()
    private val requestOptions = RequestOptions().placeholder(R.drawable.outline_image_24)
    fun bind(movie: Movie) {
        textItemName.text = movie.movieShort?.title
        textItemYear.text = movie.movieShort?.year.toString()
        if (movie.movieShort?.rating!! >= 7.0) {
            filmRatingBackground.setBackgroundResource(R.color.rating_green)
        } else {
            filmRatingBackground.setBackgroundResource(R.color.rating_gray)
        }

        textRatingInt.text =
            if (movie.movieShort.rating.toString() == "10.0") "10" else movie.movieShort.rating.toString()

        Glide.with(itemView.context)
            .setDefaultRequestOptions(requestOptions)
            .load(movie.movieShort.imgUrl)
            .into(imageViewItem)

        btnAddFavour.setOnClickListener {
            val builder = AlertDialog.Builder(itemView.context)
            builder.setMessage("Вы добавили ${movie.movieShort.title} вы получите миска рис плюс кошкожена")
            builder.setPositiveButton("Пасыба") { dialogWindow, _ -> dialogWindow.cancel() }
            val AlertDialog: AlertDialog = builder.create()
            AlertDialog.show()
        }
    }
}