package fr.test.cyllene.repository

import androidx.lifecycle.LiveData
import fr.test.cyllene.api.BookService
import fr.test.cyllene.database.BookDao
import fr.test.cyllene.di.components.DaggerApiComponent
import fr.test.cyllene.model.Book
import fr.test.cyllene.model.Favorite
import io.reactivex.Single
import javax.inject.Inject

class Repository ( private val bookDao : BookDao) {

    @Inject
    lateinit var bookService: BookService

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun insertBooks(book: List<Book>) {
        bookDao.insertBooks(book)
    }

    fun getBookById(id: String) : LiveData <Book> {
        return bookDao.getBookById(id)
    }

    fun getBooks() : LiveData <List<Book>> {
        return bookDao.getBooks()
    }

    fun insertFavorite(favorite: Favorite){
        bookDao.insertFavorite(favorite)
    }

    fun isFavoriteRowExist(bookId : String) : Boolean{
        return bookDao.isFavoriteRowExist(bookId)
    }

    fun deleteFavorite(bookId : String){
        bookDao.deleteFavorite(bookId)
    }

    fun getFavoriteList() : LiveData <List<Book>>{
        return bookDao.getFavoriteList()
    }

    fun fetchBooks() : Single<List<Book>> {
        return bookService.api.fetchBooks()
    }

}
