package com.example.starwarswiki.ui.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.starwarswiki.core.Film
import com.example.starwarswiki.core.Person
import com.example.starwarswiki.core.Planet
import com.example.starwarswiki.core.Starship
import com.example.starwarswiki.core.converter.ListConverter

@Database(
    version = 2,
    entities = [
        Person::class,
        Starship::class,
        Film::class,
        Planet::class
    ]
)
@TypeConverters(ListConverter::class)
abstract class FavouritesDatabase : RoomDatabase() {
    abstract fun favouritesDao(): FavouritesDao

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
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return instance
            }
        }
    }
}