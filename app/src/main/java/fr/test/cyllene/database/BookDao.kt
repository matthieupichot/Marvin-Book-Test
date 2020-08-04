package fr.test.cyllene.database

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.test.cyllene.model.Book

@Dao
interface BookDao {

    @Insert
    fun insertBook(book: Book)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBooks(book: List<Book>)

    @Update
    fun updateBooks(book: List<Book>)

    @Query("SELECT * FROM book")
    fun getAllBooks() : LiveData<List<Book>>

    @Query("SELECT * FROM book WHERE volume = :volume")
    fun getBooksById(volume: Int) : LiveData<Book>
}