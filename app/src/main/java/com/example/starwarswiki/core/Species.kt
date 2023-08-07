package com.example.starwarswiki.core

import com.google.gson.annotations.SerializedName

data class Species(
    //    Search Field name
    @SerializedName("name")
    val name: String,

    @SerializedName("classification")
    val classification: String,

    @SerializedName("designation")
    val designation: String,

    @SerializedName("average_height")
    val averageHeight: String,

    @SerializedName("average_lifespan")
    val averageLifespan: String,

    @SerializedName("eye_colors")
    val eyeColors: String = TAG_NONE,

    @SerializedName("hair_colors")
    val hairColors: String = TAG_NONE,

    @SerializedName("skin_colors")
    val skin_colors: String = TAG_NONE,

    @SerializedName("language")
    val language: String,

    @SerializedName("homeworld")
    val homeWorld: String,

    @SerializedName("people")
    val people: ArrayList<Person>,

    @SerializedName("films")
    val films: ArrayList<Film>,

    @SerializedName("url")
    val url: String,

    @SerializedName("created")
    val created: String,

    @SerializedName("edited")
    val edited: String
) {
    companion object {
        const val TAG_NONE = "none"
    }
}
