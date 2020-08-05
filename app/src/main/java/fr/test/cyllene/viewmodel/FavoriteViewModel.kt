package fr.test.cyllene.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fr.test.cyllene.model.Book
import fr.test.cyllene.repository.Repository

class FavoriteViewModel (private var repository: Repository) : ViewModel() {

    fun getFavoriteList() : LiveData<List<Book>> {
        return repository.getFavoriteList()
    }

}