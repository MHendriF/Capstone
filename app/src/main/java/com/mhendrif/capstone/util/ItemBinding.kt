package com.mhendrif.capstone.util

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.mhendrif.capstone.R

object ItemBinding {
    @JvmStatic
    @BindingAdapter("android:favoriteImage")
    fun setFavoriteImage(view: View, isFavorite: Boolean?) {
        val imageView: ImageView = view.findViewById(R.id.ivFavorite)
        isFavorite?.apply {
            imageView.setImageDrawable(
                ContextCompat.getDrawable(
                    view.context,
                    if (isFavorite) R.drawable.ic_delete else R.drawable.ic_favorite
                )
            )
        }
    }
}
