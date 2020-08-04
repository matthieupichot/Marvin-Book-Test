package fr.test.cyllene.database

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.test.cyllene.model.Book

@Database(entities = [Book::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao() : BookDao
}