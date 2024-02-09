package fr.test.cyllene.database

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.test.cyllene.model.Book
import fr.test.cyllene.model.Favorite

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBooks(book: List<Book>)

    @Query("SELECT * FROM book")
    fun getBooks() : LiveData <List<Book>>

    @Query("SELECT * FROM book WHERE id = :id")
    fun getBookById(id: Int) : LiveData <Book>

    @Query("SELECT * FROM book INNER JOIN favorite ON Book.id = favorite.bookId")
    fun getFavoriteList() : LiveData <List<Book>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favorite: Favorite)

    @Query("DELETE FROM favorite WHERE bookId = :bookId")
    fun deleteFavorite(bookId : Int)

    @Query("SELECT EXISTS(SELECT * FROM favorite WHERE bookId = :bookId)")
    fun isFavoriteRowExist(bookId : Int) : Boolean

}