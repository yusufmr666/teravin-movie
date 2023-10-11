package com.example.teravinmovie.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teravinmovie.data.remote.response.ResultsItem

@Database(
    entities = [ResultsItem::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase :RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): MovieDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie.db").build()
            }
        }
    }
}