package com.mhendrif.capstone.core.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.mhendrif.capstone.core.utils.Constants
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

object ImageBinding {
    @BindingAdapter("android:posterURL")
    @JvmStatic
    fun setPosterURL(imageView: ImageView, URL: String?) {
        try {
            imageView.alpha = 0f
            Picasso.get().load(Constants.API_POSTER_PATH+URL).noFade().into(imageView, object :
                Callback {
                override fun onSuccess() {
                    imageView.animate().setDuration(300).alpha(1f).start()
                }
                override fun onError(e: Exception?) {}
            })
        } catch (ignored: Exception) { }
    }

    @BindingAdapter("android:backdropURL")
    @JvmStatic
    fun setBackdropURL(imageView: ImageView, URL: String?) {
        try {
            imageView.alpha = 0f
            Picasso.get().load(Constants.API_BACKDROP_PATH+URL).noFade().into(imageView, object :
                Callback {
                override fun onSuccess() {
                    imageView.animate().setDuration(300).alpha(1f).start()
                }
                override fun onError(e: Exception?) {}
            })
        } catch (ignored: Exception) { }
    }
}