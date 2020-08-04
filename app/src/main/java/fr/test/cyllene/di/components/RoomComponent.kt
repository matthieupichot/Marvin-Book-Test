package fr.test.cyllene.di.components

import dagger.Component
import fr.test.cyllene.di.modules.RoomModule
import fr.test.cyllene.view.fragments.HomeFragment
import fr.test.cyllene.viewmodel.HomeViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [RoomModule::class])

interface RoomComponent {

    fun inject(homeFragment: HomeFragment)

}