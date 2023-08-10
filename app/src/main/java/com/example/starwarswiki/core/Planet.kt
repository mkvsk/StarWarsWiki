package com.example.starwarswiki.core

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.starwarswiki.core.converter.ListConverter
import com.google.gson.annotations.SerializedName

@Entity
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
    @TypeConverters(ListConverter::class)
    val residents: List<String>,

    @SerializedName("films")
    @TypeConverters(ListConverter::class)
    val films: List<String>,

    @SerializedName("url")
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    val url: String,

    @SerializedName("created")
    val created: String,

    @SerializedName("edited")
    val edited: String
)
