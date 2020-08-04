package fr.test.cyllene.di.components

import dagger.Component
import fr.test.cyllene.api.BookService
import fr.test.cyllene.di.modules.ApiModule
import fr.test.cyllene.repository.Repository
import fr.test.cyllene.viewmodel.HomeViewModel
import fr.test.cyllene.viewmodel.LoginViewModel


@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: BookService)

    fun inject(repository: Repository)

    fun inject(homeViewModel: HomeViewModel)

    fun inject(loginViewModel: LoginViewModel)

}