package com.example.moviehelloween.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworkviewstp2.model.Civitai
import com.example.homeworkviewstp2.model.CivitaiResponse
import com.example.homeworkviewstp2.retrofit.common.CommonCivitai
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class CivitaiViewModel : ViewModel() {
    private val _civitaiList = MutableStateFlow<List<Civitai>>(emptyList())
    val civitaiList: StateFlow<List<Civitai>> get() = _civitaiList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    private val mService = CommonCivitai.retrofitServiecesCivitai

    var currentPage = 50

    fun fetchCivitaiList(page: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = mService.getImagesList(page) // suspend вызов
                _civitaiList.value = response.items ?: emptyList()
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}