package fr.test.cyllene.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(uri: String?) {
    Glide.with(this.context)
        .load(uri)
        .centerCrop()
        .into(this)
}