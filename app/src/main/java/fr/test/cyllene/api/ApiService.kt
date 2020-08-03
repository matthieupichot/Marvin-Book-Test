package fr.test.cyllene.api

import fr.test.cyllene.model.Book
import fr.test.cyllene.model.User
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("Vy0sPkEvO")
    fun getBooks(): Single<List<Book>>

    @GET("4JcjYCeWY")
    fun getUsers(): Single<List<User>>

}