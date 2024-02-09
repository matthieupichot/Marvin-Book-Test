package fr.test.cyllene.utils

import fr.test.cyllene.api.ApiService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtil {
    private const val BASE_URL = "https://api.json-generator.com/templates/"

    fun createApiService(): ApiService {

        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return builder.create(ApiService::class.java)
    }

}