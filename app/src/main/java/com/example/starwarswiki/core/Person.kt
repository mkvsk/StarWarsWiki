package com.example.starwarswiki.core

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.starwarswiki.core.converter.ListConverter
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
    @TypeConverters(ListConverter::class)
    val films: List<String> = mutableListOf(),

    @SerializedName("species")
    @TypeConverters(ListConverter::class)
    val species: List<String> = mutableListOf(),

    @SerializedName("starships")
    @TypeConverters(ListConverter::class)
    val starships: List<String> = mutableListOf(),

    @SerializedName("vehicles")
    @TypeConverters(ListConverter::class)
    val vehicles: List<String> = mutableListOf(),

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