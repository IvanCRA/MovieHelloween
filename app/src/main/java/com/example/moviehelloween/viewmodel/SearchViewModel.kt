package com.example.moviehelloween.viewmodel

import android.content.Context
import android.service.autofill.FillEventHistory
import androidx.lifecycle.ViewModel
import com.example.moviehelloween.Movie
import com.example.moviehelloween.data.Cache
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SearchViewModel(private val context: Context) : ViewModel() {
    private lateinit var file: Cache
    private var listData = listOf<String>()

    init {
        createFile()
    }

    private fun createFile() {
        file = Cache(context)
    }

    fun openFile(): List<String> {
        listData = file.readHistory("history_of_search.txt")
        return listData
    }

    fun saveFile(listHistory: MutableList<String>) {
        file.saveHistory(listHistory, filename = "history_of_search.txt")
    }
}
