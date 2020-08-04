package fr.test.cyllene.di.modules

import dagger.Module
import dagger.Provides
import fr.test.cyllene.api.ApiService
import fr.test.cyllene.api.BookService

import fr.test.cyllene.utils.RetrofitUtil

@Module
class ApiModule {

    @Provides
    fun provideApi(): ApiService {
        return RetrofitUtil.createApiService()
    }

    @Provides
    fun provideService(): BookService {
        return BookService()
    }

}