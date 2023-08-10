package com.example.starwarswiki.ui.local

import com.example.starwarswiki.core.Film
import com.example.starwarswiki.core.Person
import com.example.starwarswiki.core.Planet
import com.example.starwarswiki.core.Starship
import kotlinx.coroutines.flow.Flow

class FavouritesRepository(private val dao: FavouritesDao) {
    fun getAllPeople(): Flow<List<Person>> {
        return dao.getAllPeople()
    }

    suspend fun insert(person: Person): Long {
        return dao.insertPerson(person)
    }

    suspend fun delete(person: Person): Int {
        return dao.deletePerson(person)
    }

    fun getAllStarship(): Flow<List<Starship>> {
        return dao.getAllStarships()
    }

    suspend fun insert(starship: Starship): Long {
        return dao.insertStarship(starship)
    }

    suspend fun delete(starship: Starship): Int {
        return dao.deleteStarship(starship)
    }

    fun getAllFilms(): Flow<List<Film>> {
        return dao.getAllFilms()
    }

    suspend fun insert(film: Film): Long {
        return dao.insertFilm(film)
    }

    suspend fun delete(film: Film): Int {
        return dao.deleteFilm(film)
    }

    fun getAllPlanets(): Flow<List<Planet>> {
        return dao.getAllPlanets()
    }

    suspend fun insert(planet: Planet): Long {
        return dao.insertPlanet(planet)
    }

    suspend fun delete(planet: Planet): Int {
        return dao.deletePlanet(planet)
    }
}