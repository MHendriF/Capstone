package com.mhendrif.capstone.core.utils

import android.app.AlertDialog
import android.content.Context
import com.mhendrif.capstone.R

object DialogMessage {
    fun showDialog(context: Context, title: String?, isFavorite: Boolean, `fun`: () -> Unit) {
        AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage("Do you want to ${if (isFavorite) "delete" else "add"} $title ${if (isFavorite) "from" else "to"} favorite?")
                .setNegativeButton(context.getString(R.string.cancel), null)
                .setPositiveButton(context.getString(R.string.ok)) { _, _ ->
                    `fun`.invoke()
                }.show()
    }
}