package fr.test.cyllene.api

import fr.test.cyllene.model.Book
import fr.test.cyllene.model.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("5xNfZ_KhwYdB/data")
    fun fetchBooks(@Query("access_token") access_token : String): Single<List<Book>>

    @GET("B0aKmVbAqhVw/data")
    fun getUsers(@Query("access_token") access_token : String): Single<List<User>>
}