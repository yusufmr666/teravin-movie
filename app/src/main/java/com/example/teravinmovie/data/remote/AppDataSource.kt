package com.example.teravinmovie.data.remote

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.teravinmovie.data.remote.response.ResultsItem
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface AppDataSource {
    fun getMovie(api_key: String): LiveData<PagingData<ResultsItem>>
}