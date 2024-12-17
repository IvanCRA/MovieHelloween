package com.example.moviehelloween

import android.content.Context
import android.util.Log
import com.example.moviehelloween.fragment.HomeFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class MovieRepository(private val context: Context) {
    fun loadMovies() : List<Movie> {
        val movieList = mutableListOf<Movie>()

        for(i in 1..6) {
            val jsonInputStream = context.assets.open("movie$i.json")
            val reader = InputStreamReader(jsonInputStream)
            val movie = Gson().fromJson<Movie>(reader, object : TypeToken<Movie>() {}.type)
            movieList.add(movie)
            reader.close()
        }
        return movieList
    }
}