package com.mhendrif.capstone.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mhendrif.capstone.R
import com.mhendrif.capstone.core.domain.model.Movie
import com.mhendrif.capstone.databinding.ItemContainerBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_container, parent, false))

    override fun onBindViewHolder(holder: MovieAdapter.ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount() = listData.size

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemContainerBinding.bind(itemView)
        fun bind(data: Movie) {
            with(binding) {
                tvTitle.text = data.title
                tvReleaseDate.text = data.releaseDate
                tvScore.text = data.voteAverage.toString()
            }
        }
    }

}