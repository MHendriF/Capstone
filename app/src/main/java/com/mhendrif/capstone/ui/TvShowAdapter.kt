package com.mhendrif.capstone.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mhendrif.capstone.common.util.Constants
import com.mhendrif.capstone.core.R
import com.mhendrif.capstone.core.databinding.ItemContainerBinding
import com.mhendrif.capstone.core.utils.ImageBinding
import com.mhendrif.capstone.domain.model.TvShow

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.ListViewHolder>() {

    private var listData = ArrayList<TvShow>()
    var onItemClick: ((TvShow) -> Unit)? = null

    fun setData(newListData: List<TvShow>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_container, parent, false)
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemContainerBinding.bind(itemView)
        fun bind(data: TvShow) {
            with(binding) {
                tvTitle.text = data.title
                tvReleaseDate.text = data.releaseDate
                tvScore.text = data.voteAverage.toString()
                ImageBinding.setImageURL(ivPoster, Constants.API_POSTER_PATH + data.posterPath)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}