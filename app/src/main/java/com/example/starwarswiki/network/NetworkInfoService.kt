package com.example.starwarswiki.network

import com.example.starwarswiki.core.Film
import com.example.starwarswiki.network.response.GetAllFilmsResponse
import com.example.starwarswiki.network.response.GetAllPeopleResponse
import com.example.starwarswiki.network.response.GetAllPlanetsResponse
import com.example.starwarswiki.network.response.GetAllStarshipsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface NetworkInfoService {
    @GET("people/")
    fun getAllPeople(): Call<GetAllPeopleResponse>

    @GET("starships/")
    fun getAllStarships(): Call<GetAllStarshipsResponse>

    @GET("films/")
    fun getAllFilms(): Call<GetAllFilmsResponse>

    @GET("planets/")
    fun getAllPlanets(): Call<GetAllPlanetsResponse>

    @GET
    fun getFilm(@Url url: String): Call<Film>
}