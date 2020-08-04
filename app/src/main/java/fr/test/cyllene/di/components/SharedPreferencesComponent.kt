package fr.test.cyllene.di.components

import dagger.Component
import fr.test.cyllene.di.modules.SharedPreferencesModule
import fr.test.cyllene.view.activities.LoginActivity
import fr.test.cyllene.view.activities.SplashActivity

@Component(modules = [SharedPreferencesModule::class])
interface SharedPreferencesComponent {

    fun inject(loginActivity: LoginActivity)

    fun inject(splashActivity: SplashActivity)

}