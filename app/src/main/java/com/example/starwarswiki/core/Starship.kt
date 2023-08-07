package com.example.starwarswiki.core

import com.google.gson.annotations.SerializedName

data class Starship(
    //    Search Field name, model
    @SerializedName("name")
    val name: String,

    @SerializedName("model")
    val model: String,

    @SerializedName("starship_class")
    val starshipClass: String,

    @SerializedName("manufacturer")
    val manufacturer: String,

    @SerializedName("cost_in_credits")
    val costInCredits: String,

    @SerializedName("length")
    val length: String,

    @SerializedName("crew")
    val crew: String,

    @SerializedName("passengers")
    val passengers: String,

    @SerializedName("max_atmosphering_speed")
    val maxAtmospheringSpeed: String,

    @SerializedName("hyperdrive_rating")
    val hyperDriveRating: String,

    @SerializedName("MGLT")
    val MGLT: String,

    @SerializedName("cargo_capacity")
    val cargoCapacity: String,

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
