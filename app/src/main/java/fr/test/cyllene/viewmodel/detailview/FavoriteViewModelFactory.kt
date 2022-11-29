package fr.test.cyllene.viewmodel.detailview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.test.cyllene.repository.Repository
import fr.test.cyllene.viewmodel.favoriteview.FavoriteViewModel

class FavoriteViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        FavoriteViewModel(repository) as T
}