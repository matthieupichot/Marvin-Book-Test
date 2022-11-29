package fr.test.cyllene.viewmodel.detailview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.test.cyllene.repository.Repository

class DetailViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        DetailViewModel(repository) as T
}