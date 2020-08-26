package fr.test.cyllene.viewmodel.homeview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.test.cyllene.model.Book
import fr.test.cyllene.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class HomeViewModel (private var repository: Repository) : ViewModel() {

    private val disposable = CompositeDisposable()
    val data = MutableLiveData<List<Book>>()
    val error = MutableLiveData<String>()

    fun fetchBooks(){
        disposable.add(
            repository.fetchBooks()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Book>>(){
                    override fun onSuccess(value: List<Book>) {
                        data.postValue(value)
                    }
                    override fun onError(e: Throwable) {
                        error.postValue(e.message + e.cause)
                    }
                })
        )
    }

    fun insertBooks(list: List<Book>) {
        repository.insertBooks(list)
    }

    fun getBooks() : LiveData<List<Book>> {
        return repository.getBooks()
    }
}