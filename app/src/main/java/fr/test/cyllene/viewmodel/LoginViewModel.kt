package fr.test.cyllene.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.test.cyllene.api.BookService
import fr.test.cyllene.di.components.DaggerApiComponent
import fr.test.cyllene.model.User
import fr.test.cyllene.utils.Constants
import fr.test.cyllene.utils.SharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel : ViewModel() {

    @Inject
    lateinit var bookService : BookService

    init {
        DaggerApiComponent.create().inject(this)
    }

    private val disposable = CompositeDisposable()
    val login = MutableLiveData<String>()

    fun login(email: String, password: String, sharedPreferences : SharedPreferences){
        disposable.add(
            bookService.api.getUsers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<User>>(){
                    override fun onSuccess(value: List<User>) {
                        if(isCredentialsValid(email, password, value, sharedPreferences)) login.postValue(Constants.LOGIN_SUCCESSFUL)
                        else login.postValue(Constants.LOGIN_FAILED)
                    }
                    override fun onError(e: Throwable) {
                        login.postValue(Constants.LOGIN_FAILED)
                    }
                })
        )
    }

    private fun isCredentialsValid(email: String, password: String, value: List<User>, sharedPreferences : SharedPreferences) : Boolean {
        var valid = false
        for(user in value){
            if(email == user.email && password == user.password){
                valid = true
                saveUsername(user.name, sharedPreferences)
                break
            }
        }
        return valid
    }

    private fun saveUsername(username : String, sharedPreferences : SharedPreferences) {
        sharedPreferences.saveUsername(Constants.USERNAME, username)
    }

}