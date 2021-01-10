package com.mhendrif.capstone.ui

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.mhendrif.capstone.R
import com.mhendrif.capstone.ui.base.BaseAdapter
import com.mhendrif.capstone.databinding.ItemContainerTvShowBinding
import com.mhendrif.capstone.domain.model.TvShow
import kotlin.math.roundToInt

class TvShowAdapter : BaseAdapter<TvShow, ItemContainerTvShowBinding>(R.layout.item_container_tv_show, diffUtil) {
    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<TvShow>() {
            override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow) =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow) =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: Holder<ItemContainerTvShowBinding>, position: Int) {
        holder.binding?.let { bind ->
            getItem(position)?.apply {
                bind.pbScore.progress = (this.voteAverage * 10).roundToInt()
                bind.model = this
                bind.root.setOnClickListener { onItemListener?.onItemClick(this) }
            }
        }
    }
}