package com.example.starwarswiki.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.starwarswiki.core.Film
import com.example.starwarswiki.core.Person
import com.example.starwarswiki.core.Planet
import com.example.starwarswiki.core.Starship
import com.example.starwarswiki.ui.local.FavouritesRepository
import kotlinx.coroutines.launch

class FavouritesViewModel(
    private val repository: FavouritesRepository
) : ViewModel() {
    val people = repository.getAllPeople().asLiveData()
    val starship = repository.getAllStarship().asLiveData()
    val films = repository.getAllFilms().asLiveData()
    val planets = repository.getAllPlanets().asLiveData()

    private var _favData = MutableLiveData<ArrayList<Any>?>()
    val favData: LiveData<ArrayList<Any>?> get() = _favData

    private fun setAllData(value: ArrayList<Any>?) {
        value?.let { _favData.value = it }
    }

    fun fetchFavData() = liveData {
        repository.getAllPeople().collect {
            emit(it)
        }
    }

    fun insert(value: Any) = viewModelScope.launch {
        var newRowId: Long? = null
        when (value) {
            is Person -> {
                newRowId = repository.insert(person = value)
            }

            is Starship -> {
                newRowId = repository.insert(starship = value)
            }

            is Film -> {
                newRowId = repository.insert(film = value)
            }

            is Planet -> {
                newRowId = repository.insert(planet = value)
            }
        }

        if (newRowId != null && newRowId > -1) {
            Log.d("TAG", "Subscriber Inserted Successfully $newRowId")
        } else {
            Log.d("TAG", "Error Occurred")
        }
    }

    fun delete(value: Any) = viewModelScope.launch {
        if (value is Person) {
            repository.delete(person = value)
        } else if (value is Starship) {
            repository.delete(starship = value)
        } else if (value is Film) {
            repository.delete(film = value)
        } else if (value is Planet) {
            repository.delete(planet = value)
        }
    }


}