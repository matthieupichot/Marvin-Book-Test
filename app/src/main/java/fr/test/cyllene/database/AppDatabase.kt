package fr.test.cyllene.database

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.test.cyllene.model.Book
import fr.test.cyllene.model.Favorite

@Database(entities = [Book::class, Favorite::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao() : BookDao
}