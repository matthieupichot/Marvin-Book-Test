package fr.test.cyllene.viewmodel.detailview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fr.test.cyllene.model.Book
import fr.test.cyllene.model.Favorite
import fr.test.cyllene.repository.Repository

class DetailViewModel (private var repository: Repository) : ViewModel() {

    fun getBookById(id: Int): LiveData <Book> {
        return repository.getBookById(id)
    }

    fun getBooks(): LiveData <List<Book>> {
        return repository.getBooks()
    }

    fun insertFavorite(favorite: Favorite){
        repository.insertFavorite(favorite)
    }

    fun isFavoriteRowExist(bookId : Int) : Boolean{
        return  repository.isFavoriteRowExist(bookId)
    }

    fun deleteFavorite(bookId : Int){
        repository.deleteFavorite(bookId)
    }

    fun getFavoriteList() : LiveData <List<Book>>{
        return repository.getFavoriteList()
    }

}