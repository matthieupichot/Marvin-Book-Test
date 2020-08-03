package fr.test.cyllene.view

import android.app.Application
import fr.test.cyllene.di.components.DaggerSharedPreferencesComponent
import fr.test.cyllene.di.components.SharedPreferencesComponent
import fr.test.cyllene.di.modules.SharedPreferencesModule

class Application : Application() {

    var sharedPreferencesComponent: SharedPreferencesComponent? = null

    override fun onCreate() {
        super.onCreate()
        sharedPreferencesComponent = DaggerSharedPreferencesComponent.builder()
            .sharedPreferencesModule(SharedPreferencesModule(applicationContext))
            .build()
    }

}