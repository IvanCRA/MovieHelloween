package com.example.moviehelloween.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviehelloween.Favourite
import com.example.moviehelloween.FavouriteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavouriteViewModel (private val repository: FavouriteRepository) : ViewModel() {
    private val _favourite = MutableStateFlow<List<Favourite>>(emptyList())
    val favourite: StateFlow<List<Favourite>> = _favourite

    init {
        loadFavourites()
    }

    private fun loadFavourites() {
        viewModelScope.launch {
            val favouriteList = repository.loadFavourites()
            _favourite.value = favouriteList
        }
    }
}