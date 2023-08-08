package com.example.starwarswiki.ui.local

import androidx.lifecycle.MutableLiveData
import com.example.starwarswiki.core.Person
import com.example.starwarswiki.core.Starship
import kotlinx.coroutines.flow.Flow

class FavouritesRepository(private val dao: FavouritesDao) {
//    private lateinit var _people: Flow<List<Person>?>
//    val people: Flow<List<Person>?> get() = _people

    fun getAllPeople(): List<Person>? {
        return dao.getAllPeople().value
    }

    suspend fun insert(person: Person) {
        dao.insertPeople(person)
    }

    suspend fun delete(person: Person) {
        dao.deletePerson(person)
    }

//    private lateinit var _starships: Flow<List<Starship>?>
//    val starships: Flow<List<Starship>?> get() = _starships

    fun getAllStarship(): List<Starship>? {
        return dao.getAllStarships().value
    }

    suspend fun insert(starship: Starship) {
        dao.insertStarship(starship)
    }

    suspend fun delete(starship: Starship) {
        dao.deleteStarship(starship)
    }
}