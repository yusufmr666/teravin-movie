package com.example.teravinmovie.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teravinmovie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private lateinit var viewModel: MovieViewModel
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
        val storiesAdapter = MovieAdapter()

        val api_key = "f7b67d9afdb3c971d4419fa4cb667fbf"

        binding.rvDataListCuti.layoutManager = LinearLayoutManager(this)
        viewModel.getMovie(api_key).observe(this) {movie ->
            if (movie !== null){
                binding.rvDataListCuti.adapter = storiesAdapter.withLoadStateFooter(
                    footer = LoadingStateAdapter {
                        storiesAdapter.retry()
                    }
                )
                Toast.makeText(this, "Data Baru ditemukan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
            }


            viewModel.getMovie(api_key).observe(this) {
                storiesAdapter.submitData(lifecycle, it)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}