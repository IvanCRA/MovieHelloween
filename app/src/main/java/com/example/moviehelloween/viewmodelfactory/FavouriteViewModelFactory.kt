package com.example.moviehelloween.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviehelloween.FavouriteRepository
import com.example.moviehelloween.viewmodel.FavouriteViewModel

class FavouriteViewModelFactory(private val repository: FavouriteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavouriteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavouriteViewModel(repository) as T
        }
        throw IllegalAccessException("Unknown ViewModel class")
    }
}