package fr.test.cyllene.viewmodel.detailview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fr.test.cyllene.model.Book
import fr.test.cyllene.model.Favorite
import fr.test.cyllene.repository.Repository

class DetailViewModel (private var repository: Repository) : ViewModel() {

    fun getBookById(id: String): LiveData <Book> {
        return repository.getBookById(id)
    }

    fun getBooks(): LiveData <List<Book>> {
        return repository.getBooks()
    }

    fun insertFavorite(favorite: Favorite){
        repository.insertFavorite(favorite)
    }

    fun isFavoriteRowExist(bookId : String) : Boolean{
        return  repository.isFavoriteRowExist(bookId)
    }

    fun deleteFavorite(bookId : String){
        repository.deleteFavorite(bookId)
    }

    fun getFavoriteList() : LiveData <List<Book>>{
        return repository.getFavoriteList()
    }

}