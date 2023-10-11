package com.example.teravinmovie.data.remote.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movie")
data class ResultsItem(
    @PrimaryKey
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("release_date")
    val releaseDate: String,


)