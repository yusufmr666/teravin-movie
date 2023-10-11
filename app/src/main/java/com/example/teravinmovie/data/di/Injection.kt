package com.example.teravinmovie.data.di

import android.content.Context
import com.example.teravinmovie.data.local.MovieDatabase
import com.example.teravinmovie.data.remote.Repository
import com.example.teravinmovie.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): Repository {
        val database = MovieDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(database, apiService)
    }
}