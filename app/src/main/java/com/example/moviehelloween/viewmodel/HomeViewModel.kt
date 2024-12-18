package com.example.moviehelloween.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviehelloween.Movie
import com.example.moviehelloween.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movie = MutableStateFlow<List<Movie>>(emptyList())
    val movie: StateFlow<List<Movie>> = _movie

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            val movieList = repository.loadMovies()
            _movie.value = movieList
        }
    }
}