package com.example.teravinmovie.data.remote.retrofit

import com.example.teravinmovie.data.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("/3/discover/movie")
    suspend fun getAllMovies(
        @Query("api_key") api_key: String?,
        @Query("page") page: Int?,
        @Query("size") size: Int?,
    ): MovieResponse
}