package com.example.teravinmovie.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.*
import com.example.teravinmovie.data.local.MovieDatabase
import com.example.teravinmovie.data.remote.response.ResultsItem
import com.example.teravinmovie.data.remote.retrofit.ApiService
import okhttp3.MultipartBody
import okhttp3.RequestBody

class Repository private constructor(
    private val storyDatabase: MovieDatabase,
    private val apiService: ApiService
) : AppDataSource{

    override fun getMovie(api_key: String): LiveData<PagingData<ResultsItem>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator = MovieRemoteMediator(storyDatabase, apiService, api_key),
            pagingSourceFactory = {
                storyDatabase.movieDao().getAllMovie()
            }
        ).liveData
    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        @JvmStatic
        fun getInstance(
            storyDatabase: MovieDatabase,
            apiService: ApiService
        ) : Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(storyDatabase, apiService)
            }.also { instance = it }
    }
}