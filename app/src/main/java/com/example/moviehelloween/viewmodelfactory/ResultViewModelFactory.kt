package com.example.moviehelloween.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviehelloween.MovieRepository
import com.example.moviehelloween.viewmodel.HomeViewModel
import com.example.moviehelloween.viewmodel.ResultViewModel

class ResultViewModelFactory(
    private val repository: MovieRepository,
    private val searchQuery: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ResultViewModel(repository, searchQuery) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}