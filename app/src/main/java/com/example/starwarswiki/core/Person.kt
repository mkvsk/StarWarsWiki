package com.example.starwarswiki.core

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Person(
//    Search Field
    @SerializedName("name")
    val name: String,

    @SerializedName("birth_year")
    val birthYear: String,

    @SerializedName("eye_color")
    val eyeColor: String = TAG_UNKNOWN,

    @SerializedName("gender")
    val gender: String = TAG_UNKNOWN,

    @SerializedName("hair_color")
    val hairColor: String = TAG_UNKNOWN,

    @SerializedName("height")
    val height: String,

    @SerializedName("mass")
    val mass: String,

    @SerializedName("skin_color")
    val skinColor: String,

    @SerializedName("homeworld")
    val homeWorld: String,

    @SerializedName("films")
    val films: ArrayList<String> = ArrayList(),

    @SerializedName("species")
    val species: ArrayList<String> = ArrayList(),

    @SerializedName("starships")
    val starships: ArrayList<String> = ArrayList(),

    @SerializedName("vehicles")
    val vehicles: ArrayList<String> = ArrayList(),

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    @SerializedName("url")
    val url: String,

    @SerializedName("created")
    val created: String,

    @SerializedName("edited")
    val edited: String,

    ) {
    companion object {
        const val TAG_UNKNOWN = "n/a"
    }
}