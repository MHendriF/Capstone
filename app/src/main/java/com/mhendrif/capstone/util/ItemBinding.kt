package com.mhendrif.capstone.util

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.mhendrif.capstone.R

object ItemBinding {
    @JvmStatic
    @BindingAdapter("android:favoriteImage")
    fun setFavoriteImage(imageView: ImageView, isFavorite: Boolean?) {
        isFavorite?.apply {
            imageView.setImageDrawable(
                ContextCompat.getDrawable(
                    imageView.context,
                    if (isFavorite) R.drawable.ic_delete else R.drawable.ic_favorite
                )
            )
        }
    }
}
