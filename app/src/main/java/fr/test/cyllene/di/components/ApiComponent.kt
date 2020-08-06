package fr.test.cyllene.di.components

import dagger.Component
import fr.test.cyllene.api.BookService
import fr.test.cyllene.di.modules.ApiModule
import fr.test.cyllene.repository.Repository
import fr.test.cyllene.viewmodel.loginview.LoginViewModel


@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: BookService)

    fun inject(repository: Repository)

    fun inject(loginViewModel: LoginViewModel)

}