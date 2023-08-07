package com.example.starwarswiki.network

import com.example.starwarswiki.core.Film
import com.example.starwarswiki.core.Person
import com.example.starwarswiki.core.Starship
import com.example.starwarswiki.network.response.GetAllPeopleResponse
import com.example.starwarswiki.network.response.GetAllStarshipsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface NetworkInfoService {
    @GET("people/")
    fun getAllPeople(): Call<GetAllPeopleResponse>

//    @GET("films/")
//    fun getAllFilms(): Call<GetAllFilmsResponse>

    @GET("starships/")
    fun getAllStarships(): Call<GetAllStarshipsResponse>

    @GET
    fun getFilm(@Url url: String): Call<Film>
}