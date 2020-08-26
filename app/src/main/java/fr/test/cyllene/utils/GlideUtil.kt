package fr.test.cyllene.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import fr.test.cyllene.model.Book
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


fun loadImage(book : Book, view : ImageView, context: Context) {

    val path = view.context.filesDir.absolutePath + "/" + "images"
    val directory = File(path)
    val fileName = book.title + ".jpeg"
    val imageFile = File(directory, fileName)

    if (!directory.exists()) {
        directory.mkdir()
    }

    if (directory.exists()) {
        if (imageFile.exists()) {
            loadImageFromInternalStorage(imageFile, view, context)
        } else {
            fetchImage(book, imageFile, view, context)
        }
    }

}

fun loadImageFromInternalStorage(imageFile : File, view : ImageView, context: Context){
    Glide.with(context)
        .load(imageFile)
        .centerCrop()
        .into(view)
}

fun fetchImage(book: Book, imageFile : File, view : ImageView, context: Context){
    Glide.with(context)
        .asBitmap()
        .load(book.imageUrl)
        .centerCrop()
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                saveImage(resource, imageFile)
                if(imageFile.exists()){
                    loadImageFromInternalStorage(imageFile, view, context)
                }
            }
            override fun onLoadCleared(placeholder: Drawable?) {
            }
        })
}

fun saveImage(resource: Bitmap, imageFile: File){
    try {
        val fOut: OutputStream = FileOutputStream(imageFile)
        resource.compress(Bitmap.CompressFormat.JPEG, 100, fOut)
        fOut.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}






