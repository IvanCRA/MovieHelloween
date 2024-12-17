package com.example.moviehelloween

data class Movie(
    val id: Int?,
    val movieShort: MovieShort?,
    val desc: String?,
    val isWatchable: Boolean?,
    val bigImgUrl: String?,
    val tags: List<Tag?>?
)

data class MovieShort(
    val id: Int?,
    val title: String?,
    val year: Int?,
    val rating: Float?,
    val imgUrl: String?,
)
data class Tag(
    val id: Int?,
    val title: String?,
    val color: Long?,
)