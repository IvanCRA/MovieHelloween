package com.example.moviehelloween.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviehelloween.Movie
import com.example.moviehelloween.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ResultViewModel(private val repository: MovieRepository, private val searchQuery: String) : ViewModel() {
    private val _movieList = MutableStateFlow<List<Movie>>(emptyList())
    val movieList: StateFlow<List<Movie>> = _movieList

    init {
        loadMovie()
    }
    private fun loadMovie(){
        val resultList = repository.loadMovies().filter {
            it.movieShort?.title?.lowercase()!!.contains(searchQuery.lowercase())
        }
        _movieList.value = resultList
    }
}
