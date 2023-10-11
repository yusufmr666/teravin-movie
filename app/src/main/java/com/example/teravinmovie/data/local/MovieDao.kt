package com.example.teravinmovie.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teravinmovie.data.remote.response.ResultsItem

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<ResultsItem>)

    @Query("SELECT * FROM movie ")
    fun getAllMovie(): PagingSource<Int, ResultsItem>

    @Query("DELETE FROM movie")
    suspend fun deleteAll()
}