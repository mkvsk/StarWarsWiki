package com.example.starwarswiki.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starwarswiki.callback.ResultCallback
import com.example.starwarswiki.core.Film
import com.example.starwarswiki.core.Person
import com.example.starwarswiki.core.Planet
import com.example.starwarswiki.core.Starship
import com.example.starwarswiki.network.response.GetAllFilmsResponse
import com.example.starwarswiki.network.response.GetAllPeopleResponse
import com.example.starwarswiki.network.response.GetAllPlanetsResponse
import com.example.starwarswiki.network.response.GetAllStarshipsResponse
import com.example.starwarswiki.ui.repository.FilmRepository
import com.example.starwarswiki.ui.repository.PersonRepository
import com.example.starwarswiki.ui.repository.PlanetRepository
import com.example.starwarswiki.ui.repository.StarshipRepository

class HomeViewModel : ViewModel() {
    companion object {
        private const val TAG = "HomeViewModel"
    }

    private val personRepository by lazy { PersonRepository() }
    private val starshipRepository by lazy { StarshipRepository() }
    private val planetRepository by lazy { PlanetRepository() }
    private val filmRepository by lazy { FilmRepository() }

    private val _allPeople = MutableLiveData<ArrayList<Person>?>()
    val allPeople: LiveData<ArrayList<Person>?> get() = _allPeople

    private fun setPeople(value: ArrayList<Person>) {
        _allPeople.value = value
    }

    private val _allStarships = MutableLiveData<ArrayList<Starship>?>()
    val allStarships: LiveData<ArrayList<Starship>?> get() = _allStarships

    private fun setStarships(value: ArrayList<Starship>) {
        _allStarships.value = value
    }

    private val _allPlanets = MutableLiveData<ArrayList<Planet>?>()
    val allPlanets: LiveData<ArrayList<Planet>?> get() = _allPlanets

    private fun setPlanets(value: ArrayList<Planet>) {
        _allPlanets.value = value
    }

    private val _allFilms = MutableLiveData<ArrayList<Film>?>()
    val allFilms: LiveData<ArrayList<Film>?> get() = _allFilms

    private fun setFilms(value: ArrayList<Film>) {
        _allFilms.value = value
    }

    private val _allData = MutableLiveData<ArrayList<Any>>()
    val allData: LiveData<ArrayList<Any>> get() = _allData

    private fun setAllData(value: ArrayList<Any>) {
        _allData.value = value
    }

    fun getAllPeople() {
        personRepository.getPeople(object : ResultCallback<GetAllPeopleResponse> {
            override fun onResult(value: GetAllPeopleResponse?) {
                value?.let {
                    setPeople(it.results)
                }
            }

            override fun onFailure(value: GetAllPeopleResponse?) {}

        })
    }

    fun getAllStarships() {
        starshipRepository.getStarships(object : ResultCallback<GetAllStarshipsResponse> {
            override fun onResult(value: GetAllStarshipsResponse?) {
                value?.let {
                    setStarships(it.results)
                }
            }

            override fun onFailure(value: GetAllStarshipsResponse?) {}

        })
    }

    fun getAllPlanets() {
        planetRepository.getPlanets(object : ResultCallback<GetAllPlanetsResponse> {
            override fun onResult(value: GetAllPlanetsResponse?) {
                value?.let {
                    setPlanets(it.results)
                }
            }

            override fun onFailure(value: GetAllPlanetsResponse?) {}

        })
    }

    fun getAllFilms() {
        filmRepository.getFilms(object : ResultCallback<GetAllFilmsResponse> {
            override fun onResult(value: GetAllFilmsResponse?) {
                value?.let {
                    setFilms(it.results)
                }
            }

            override fun onFailure(value: GetAllFilmsResponse?) {}

        })
    }

    fun allDataToList() {
        val tmp = ArrayList<Any>()
        allFilms.value?.let { tmp.addAll(it) }
        allPeople.value?.let { tmp.addAll(it) }
        allPlanets.value?.let { tmp.addAll(it) }
        allStarships.value?.let { tmp.addAll(it) }
        setAllData(tmp)
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun setIsLoading(value: Boolean) {
        _isLoading.value = value
    }
}