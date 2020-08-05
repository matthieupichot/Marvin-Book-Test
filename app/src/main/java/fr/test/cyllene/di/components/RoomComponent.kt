package fr.test.cyllene.di.components

import dagger.Component
import fr.test.cyllene.di.modules.RoomModule
import fr.test.cyllene.view.activities.DetailActivity
import fr.test.cyllene.view.fragments.FavoriteFragment
import fr.test.cyllene.view.fragments.HomeFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [RoomModule::class])

interface RoomComponent {

    fun inject(homeFragment: HomeFragment)
    fun inject(favoriteFragment: FavoriteFragment)
    fun inject(detailActivity: DetailActivity)

}