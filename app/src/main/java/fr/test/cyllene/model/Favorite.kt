package fr.test.cyllene.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = Book::class, parentColumns = ["id"], childColumns = ["bookId"])])
data class Favorite (

    @PrimaryKey(autoGenerate = true)
    var idFavorite : Int,
    var bookId : Int
)