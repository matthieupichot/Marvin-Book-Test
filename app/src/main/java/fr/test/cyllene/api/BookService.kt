package fr.test.cyllene.api

import fr.test.cyllene.di.components.DaggerApiComponent
import javax.inject.Inject

class BookService {

    @Inject
    lateinit var api: ApiService

    init {
        DaggerApiComponent.create().inject(this)
    }

}