package fr.test.cyllene.view

import android.app.Application
import com.facebook.stetho.Stetho
import fr.test.cyllene.di.components.DaggerRoomComponent
import fr.test.cyllene.di.components.DaggerSharedPreferencesComponent
import fr.test.cyllene.di.components.RoomComponent
import fr.test.cyllene.di.components.SharedPreferencesComponent
import fr.test.cyllene.di.modules.RoomModule
import fr.test.cyllene.di.modules.SharedPreferencesModule

class Application : Application() {

    var sharedPreferencesComponent: SharedPreferencesComponent? = null

    companion object {
       lateinit var roomComponent: RoomComponent
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

        roomComponent = DaggerRoomComponent.builder()
            .roomModule(RoomModule(this))
            .build()

        sharedPreferencesComponent = DaggerSharedPreferencesComponent.builder()
            .sharedPreferencesModule(SharedPreferencesModule(applicationContext))
            .build()
    }

}