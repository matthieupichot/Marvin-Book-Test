package fr.test.cyllene.repository

import fr.test.cyllene.api.BookService
import fr.test.cyllene.database.BookDao
import fr.test.cyllene.di.components.DaggerApiComponent
import fr.test.cyllene.model.Book
import io.reactivex.Single
import javax.inject.Inject

class Repository ( private val bookDao : BookDao) {

    @Inject
    lateinit var bookService: BookService

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getBooks() : Single<List<Book>> {
        return bookService.api.getBooks()
    }

    fun insertBooks(book: List<Book>) {
        bookDao.insertBooks(book)
    }

}
