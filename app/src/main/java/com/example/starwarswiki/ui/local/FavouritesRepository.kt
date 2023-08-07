package com.example.starwarswiki.ui.local

import com.example.starwarswiki.core.Person
import com.example.starwarswiki.core.Starship
import kotlinx.coroutines.flow.Flow

class FavouritesRepository(private val dao: FavouritesDao) {
    private lateinit var _people: Flow<List<Person>?>
    val people: Flow<List<Person>?> get() = _people

    suspend fun getAllPeople() {
        _people = dao.getAllPeople()
    }

    suspend fun insert(person: Person) {
        dao.insertPeople(person)
    }

    suspend fun delete(person: Person) {
        dao.deletePerson(person)
    }

    private lateinit var _starships: Flow<List<Starship>?>
    val starships: Flow<List<Starship>?> get() = _starships

    suspend fun getAllStarship() {
        _starships = dao.getAllStarships()
    }

    suspend fun insert(starship: Starship) {
        dao.insertStarship(starship)
    }

    suspend fun delete(starship: Starship) {
        dao.deleteStarship(starship)
    }
}