package fr.test.cyllene.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Book (

    @PrimaryKey(autoGenerate = true)
    @SerializedName("volume")
    val volume: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("imageUrl")
    val imageUrl: String

)