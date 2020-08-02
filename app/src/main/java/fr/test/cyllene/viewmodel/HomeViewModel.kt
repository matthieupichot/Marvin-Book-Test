package fr.test.cyllene.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.test.cyllene.api.BookService
import fr.test.cyllene.di.components.DaggerApiComponent
import fr.test.cyllene.model.Book
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel : ViewModel() {

    @Inject
    lateinit var bookService : BookService

    init {
        DaggerApiComponent.create().inject(this)
    }

    private val disposable = CompositeDisposable()
    val data = MutableLiveData<List<Book>>()
    val error = MutableLiveData<String>()

    fun getBooks(){
        disposable.add(
            bookService.api.getBooks()
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

}