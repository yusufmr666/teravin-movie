package com.example.teravinmovie.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.teravinmovie.data.di.Injection
import com.example.teravinmovie.data.remote.Repository

class ViewModelFactory private constructor(
    private val repository: Repository
    ) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(repository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }
}