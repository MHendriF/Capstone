package com.mhendrif.capstone.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mhendrif.capstone.R
import com.mhendrif.capstone.core.binding.ImageBinding
import com.mhendrif.capstone.core.domain.model.TvShow
import com.mhendrif.capstone.core.utils.Constants
import com.mhendrif.capstone.databinding.ItemContainerBinding

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.ListViewHolder>(){

    private var listData = ArrayList<TvShow>()
    var onItemClick: ((TvShow) -> Unit)? = null

    fun setData(newListData: List<TvShow>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_container, parent, false))

    override fun onBindViewHolder(holder: TvShowAdapter.ListViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemContainerBinding.bind(itemView)
        fun bind(data: TvShow) {
            with(binding) {
                tvTitle.text = data.title
                tvReleaseDate.text = data.releaseDate
                tvScore.text = data.voteAverage.toString()
                ImageBinding.setImageURL(ivPoster, Constants.API_POSTER_PATH+data.posterPath)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}