package fr.test.cyllene.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import fr.test.cyllene.database.AppDatabase
import javax.inject.Singleton

@Module
class RoomModule constructor(private val context: Context) {
    @Provides
    @Singleton
    fun providesDatabase(): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "db")
            .build()
    }
}