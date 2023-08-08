package com.example.starwarswiki.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarswiki.core.Person
import com.example.starwarswiki.core.Starship
import com.example.starwarswiki.ui.local.FavouritesRepository
import kotlinx.coroutines.launch

class FavouritesViewModel(private val repository: FavouritesRepository) : ViewModel() {
    private val _person = MutableLiveData<Person>()
    val person: LiveData<Person> get() = _person

    fun setPerson(value: Person) {
        _person.value = value
    }

    private val _starship = MutableLiveData<Starship>()
    val starship: LiveData<Starship> get() = _starship

    fun setStarship(value: Starship) {
        _starship.value = value
    }

    fun insertPerson(person: Person) = viewModelScope.launch {
        repository.insert(person)
    }

    fun insertStarship(starship: Starship) = viewModelScope.launch {
        repository.insert(starship)
    }

    private val _allPeople = MutableLiveData<List<Person>>()
    val allPeople: LiveData<List<Person>> get() = _allPeople

     fun setAllPeople() {
        _allPeople.value = repository.getAllPeople()
    }


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