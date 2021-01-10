package com.mhendrif.capstone.ui.base

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mhendrif.capstone.core.util.ItemListener

abstract class BaseAdapter<Model : Parcelable, ItemBinding : ViewDataBinding>(
    @LayoutRes private val itemLayout: Int,
    diffUtil: DiffUtil.ItemCallback<Model>,
    private val onItemClick: ItemListener<Model>? = null
) :
    ListAdapter<Model, BaseAdapter<Model, ItemBinding>.Holder<ItemBinding>>(diffUtil) {

    var onItemListener: ItemListener<Model>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder<ItemBinding> {
        return Holder(LayoutInflater.from(parent.context).inflate(itemLayout, parent, false))
    }

    inner class Holder<ItemBinding : ViewDataBinding>(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var binding = DataBindingUtil.bind<ItemBinding>(itemView)
    }
}