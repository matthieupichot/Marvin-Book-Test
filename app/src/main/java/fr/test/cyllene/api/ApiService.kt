package fr.test.cyllene.api

import fr.test.cyllene.model.Book
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("coAWABywGG")
    fun getBooks(): Single<List<Book>>

}