package com.example.teravinmovie.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.teravinmovie.data.remote.Repository
import com.example.teravinmovie.data.remote.response.ResultsItem

class MovieViewModel(private val repository: Repository) : ViewModel() {
    fun getMovie(api_key: String) : LiveData<PagingData<ResultsItem>> =
        repository.getMovie(api_key).cachedIn(viewModelScope)
}