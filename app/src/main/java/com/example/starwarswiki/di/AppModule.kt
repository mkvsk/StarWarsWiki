package com.example.starwarswiki.di

import android.content.Context
import com.example.starwarswiki.FavouritesViewModelFactory
import com.example.starwarswiki.ui.local.FavouritesDao
import com.example.starwarswiki.ui.local.FavouritesDatabase
import com.example.starwarswiki.ui.local.FavouritesRepository
import com.example.starwarswiki.ui.repository.FilmRepository
import dagger.Module
import dagger.Provides

@Module
class AppModule(val context: Context) {
    @Provides
    fun provideContext(): Context {
        return context
    }



    @Provides
    fun provideFilmRepository(): FilmRepository{
        return FilmRepository()
    }

//    kkkk

    @Provides
    fun provideFavouritesDao(): FavouritesDao {
        return FavouritesDatabase.getInstance(context).favouritesDao()
    }

    @Provides
    fun provideFavouritesRepository(dao: FavouritesDao): FavouritesRepository {
        return FavouritesRepository(dao)
    }

    @Provides
    fun provideFavouritesViewModelFactory(repository: FavouritesRepository): FavouritesViewModelFactory {
        return FavouritesViewModelFactory(repository = repository)
    }
}