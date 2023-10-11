package com.example.teravinmovie.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.teravinmovie.data.remote.response.ResultsItem
import com.example.teravinmovie.data.remote.retrofit.ApiService

class MoviePagingSource (private val apiService: ApiService) : PagingSource<Int, ResultsItem>() {
    override fun getRefreshKey(state: PagingState<Int, ResultsItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultsItem> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getAllMovies("", page, params.loadSize)

            LoadResult.Page(
                data = responseData.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (responseData.results.isEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}