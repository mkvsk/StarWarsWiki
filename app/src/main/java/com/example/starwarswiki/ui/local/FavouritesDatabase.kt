package com.example.starwarswiki.ui.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.starwarswiki.core.Film
import com.example.starwarswiki.core.Person
import com.example.starwarswiki.core.Starship

@Database(
    version = 1,
    entities = [
        Person::class,
        Starship::class,
        Film::class
    ]
)
abstract class FavouritesDatabase : RoomDatabase() {
    abstract val favouritesDao: FavouritesDao

    companion object {
        @Volatile
        private var INSTANCE: FavouritesDatabase? = null
        fun getInstance(context: Context): FavouritesDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FavouritesDatabase::class.java,
                        "fav_db"
                    ).build()
                }
                return instance
            }
        }
    }
}