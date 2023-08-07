package com.example.starwarswiki.core

import com.google.gson.annotations.SerializedName

data class Vehicle(
    //    Search Field name, model
    @SerializedName("name")
    val name: String,

    @SerializedName("model")
    val model: String,

    @SerializedName("vehicle_class")
    val vehicleСlass: String,

    @SerializedName("manufacturer")
    val manufacturer: String,

    @SerializedName("length")
    val length: String,

    @SerializedName("cost_in_credits")
    val costInCredits: String,

    @SerializedName("crew")
    val crew: String,

    @SerializedName("passengers")
    val passengers: String,

    @SerializedName("max_atmosphering_speed")
    val maxAtmospheringSpeed: String,

    @SerializedName("cargo_capacity")
    val cargo_capacity: String,

    @SerializedName("consumables")
    val consumables: String,

    @SerializedName("films")
    val films: ArrayList<Film>,

    @SerializedName("pilots")
    val pilots: ArrayList<Person>,

    @SerializedName("url")
    val url: String,

    @SerializedName("created")
    val created: String,

    @SerializedName("edited")
    val edited: String
)
