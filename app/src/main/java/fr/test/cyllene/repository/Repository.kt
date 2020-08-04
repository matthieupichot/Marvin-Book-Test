package fr.test.cyllene.repository

import fr.test.cyllene.api.BookService
import fr.test.cyllene.di.components.DaggerApiComponent
import fr.test.cyllene.model.Book
import io.reactivex.Single
import javax.inject.Inject

class Repository {

    @Inject
    lateinit var bookService: BookService

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getBooks() : Single<List<Book>> {
        return bookService.api.getBooks()
    }
    
}
