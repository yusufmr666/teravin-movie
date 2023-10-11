package com.example.teravinmovie.ui

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.teravinmovie.data.remote.response.ResultsItem
import com.example.teravinmovie.databinding.ItemListBinding

class MovieAdapter :
    PagingDataAdapter<ResultsItem, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemStoryBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemStoryBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    inner class MovieViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(resultItem: ResultsItem){
            with(binding){
                tvName.text = resultItem.title
                tvUsername.text = resultItem.releaseDate
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResultsItem>() {
            override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}