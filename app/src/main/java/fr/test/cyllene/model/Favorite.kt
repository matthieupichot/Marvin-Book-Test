package fr.test.cyllene.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Favorite (

    @PrimaryKey(autoGenerate = true)
    var idFavorite : Int,
    @ForeignKey(entity = Book::class, parentColumns = ["id"], childColumns = ["bookId"])
    var bookId : Int

)