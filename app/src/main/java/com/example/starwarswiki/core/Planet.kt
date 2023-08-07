package com.example.starwarswiki.core

import com.google.gson.annotations.SerializedName

data class Planet(
    //    Search Field name
    @SerializedName("name")
    val name: String,

    @SerializedName("diameter")
    val diameter: String,

    @SerializedName("rotation_period")
    val rotationPeriod: String,

    @SerializedName("orbital_period")
    val orbitalPeriod: String,

    @SerializedName("gravity")
    val gravity: String,

    @SerializedName("population")
    val population: String,

    @SerializedName("climate")
    val climate: String,

    @SerializedName("terrain")
    val terrain: String,

    @SerializedName("surface_water")
    val surfaceWater: String,

    @SerializedName("residents")
    val residents: ArrayList<Person>,

    @SerializedName("films")
    val films: ArrayList<Film>,

    @SerializedName("url")
    val url: String,

    @SerializedName("created")
    val created: String,

    @SerializedName("edited")
    val edited: String
)
