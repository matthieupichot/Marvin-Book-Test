package fr.test.cyllene.model

import com.google.gson.annotations.SerializedName

data class Book (

    @SerializedName("volume")
    val volume: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("imageUrl")
    val imageUrl: String

)