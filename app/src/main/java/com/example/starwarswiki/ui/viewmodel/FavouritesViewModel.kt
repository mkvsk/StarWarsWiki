package com.example.starwarswiki.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarswiki.core.Person
import com.example.starwarswiki.core.Starship
import com.example.starwarswiki.ui.local.FavouritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FavouritesViewModel(private val repository: FavouritesRepository) : ViewModel() {
    private val _person = MutableLiveData<Person>()
    val person: LiveData<Person> get() = _person

    private fun setPerson(value: Person) {
        _person.value = value
    }

    private val _starship = MutableLiveData<Starship>()
    val starship: LiveData<Starship> get() = _starship

    private fun setStarship(value: Starship) {
        _starship.value = value
    }

    private fun insertPerson(person: Person) = viewModelScope.launch {
        repository.insert(person)
    }

    private fun insertStarship(starship: Starship) = viewModelScope.launch {
        repository.insert(starship)
    }

//    private var allPeople = Flow<List<Person>?>()
//
//    fun getPeople() = viewModelScope.launch {
//        allPeople = repository.getAllPeople()
//    }
//
//    private val _allStarships = MutableLiveData<ArrayList<Starship>?>()
//    val allStarships: LiveData<ArrayList<Starship>?> get() = _allStarships
//
//    private fun setStarships(value: ArrayList<Starship>) {
//        _allStarships.value = value
//    }
//
//    private val _allData = MutableLiveData<ArrayList<Any>>()
//    val allData: LiveData<ArrayList<Any>> get() = _allData
//
//    fun setAllData(value: ArrayList<Any>) {
//        _allData.value = value
//    }
}