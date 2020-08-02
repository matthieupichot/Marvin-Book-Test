package fr.test.cyllene.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(uri: String?, width : Int, height: Int) {
    Glide.with(this.context)
        .load(uri)
        .override(width,height)
        .into(this)
}