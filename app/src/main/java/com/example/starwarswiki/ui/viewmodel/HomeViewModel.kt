package com.example.starwarswiki.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starwarswiki.callback.ResultCallback
import com.example.starwarswiki.core.Person
import com.example.starwarswiki.core.Starship
import com.example.starwarswiki.ui.repository.PersonRepository
import com.example.starwarswiki.ui.repository.StarshipRepository
import com.example.starwarswiki.network.response.GetAllPeopleResponse
import com.example.starwarswiki.network.response.GetAllStarshipsResponse

class HomeViewModel : ViewModel() {
    companion object {
        private const val TAG = "HomeViewModel"
    }

    private val personRepository by lazy { PersonRepository() }
    private val starshipRepository by lazy { StarshipRepository() }

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

    private val _allData = MutableLiveData<ArrayList<Any>>()
    val allData: LiveData<ArrayList<Any>> get() = _allData

    fun setAllData(value: ArrayList<Any>) {
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

    fun getAllData() {
        if (allPeople.value!!.isNotEmpty() && allStarships.value!!.isNotEmpty()) {
            var tmp = ArrayList<Any>()
            tmp.addAll(allStarships.value!!)
            tmp.addAll(allPeople.value!!)
            setAllData(tmp)
        }
    }
}