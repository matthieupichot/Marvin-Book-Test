package fr.test.cyllene.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity (
    indices = [Index(value = ["title"],
        unique = true)]
)
data class Book (

    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @SerializedName("volume")
    val volume: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("imageUrl")
    val imageUrl: String

)