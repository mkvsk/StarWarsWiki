package com.example.starwarswiki.core

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.starwarswiki.core.converter.ListConverter
import com.google.gson.annotations.SerializedName
import java.util.Date

@Entity
data class Film(
    //    Search Field
    @SerializedName("title")
    val title: String,

    @SerializedName("episode_id")
    val episodeId: Int,

    @SerializedName("opening_crawl")
    val openingCrawl: String,

    @SerializedName("director")
    val director: String,

    @SerializedName("producer")
    val producer: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("species")
    @TypeConverters(ListConverter::class)
    val species: List<String>,

    @SerializedName("starships")
    @TypeConverters(ListConverter::class)
    val starships: List<String>,

    @SerializedName("vehicles")
    @TypeConverters(ListConverter::class)
    val vehicles: List<String>,

    @SerializedName("characters")
    @TypeConverters(ListConverter::class)
    val characters: List<String>,

    @SerializedName("planets")
    @TypeConverters(ListConverter::class)
    val planets: List<String>,

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    @SerializedName("url")
    val url: String,

    @SerializedName("created")
    val created: String,

    @SerializedName("edited")
    val edited: String
)
