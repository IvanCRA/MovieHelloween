package com.example.moviehelloween

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class FavouriteRepository(private val context: Context) {
    fun loadFavourites() : List<Favourite> {
        val favouriteList = mutableListOf<Favourite>()

        for(i in 1..2) {
            val jsonInputStream = context.assets.open("favourites$i.json")
            val reader = InputStreamReader(jsonInputStream)
            val favour = Gson().fromJson<Favourite>(reader, object : TypeToken<Favourite>() {}.type)
            favouriteList.add(favour)
            reader.close()
        }
        return favouriteList
    }
}