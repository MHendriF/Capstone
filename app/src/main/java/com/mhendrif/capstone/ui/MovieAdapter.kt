package com.mhendrif.capstone.ui

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.mhendrif.capstone.R
import com.mhendrif.capstone.base.BaseAdapter
import com.mhendrif.capstone.databinding.ItemContainerMovieBinding
import com.mhendrif.capstone.domain.model.Movie

class MovieAdapter : BaseAdapter<Movie, ItemContainerMovieBinding>(R.layout.item_container_movie, diffUtil) {
    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: Holder<ItemContainerMovieBinding>, position: Int) {
        holder.binding?.let { bind ->
            getItem(position)?.apply {
                bind.model = this
                bind.root.setOnClickListener { onItemListener?.onItemClick(this) }
            }
        }
    }
}