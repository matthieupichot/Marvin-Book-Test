package fr.test.cyllene.di.modules

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

@Module
class SharedPreferencesModule(private val context: Context) {

    @Provides
    fun provideSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences("ApplicationPreferences", Context.MODE_PRIVATE)
    }

}