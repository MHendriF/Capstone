package com.mhendrif.capstone.core.util

import android.app.AlertDialog
import android.content.Context
import com.mhendrif.capstone.common.R

object DialogMessage {
    fun showDialog(mContext: Context, title: String?, isFavorite: Boolean, `fun`: () -> Unit) {
        AlertDialog.Builder(mContext)
            .setTitle(title)
            .setMessage("Do you want to ${if (isFavorite) "delete" else "add"} ${if (isFavorite) "from" else "to"} favorites?")
            .setNegativeButton(mContext.getString(R.string.cancel), null)
            .setPositiveButton(mContext.getString(R.string.ok)) { _, _ ->
                `fun`.invoke()
            }.show()
    }
}
