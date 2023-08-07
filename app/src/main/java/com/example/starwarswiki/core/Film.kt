package com.example.starwarswiki.core

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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
    val releaseDate: Date,

    @SerializedName("species")
    val species: ArrayList<Species>,

    @SerializedName("starships")
    val starships: ArrayList<String>,

    @SerializedName("vehicles")
    val vehicles: ArrayList<String>,

    @SerializedName("characters")
    val characters: ArrayList<String>,

    @SerializedName("planets")
    val planets: ArrayList<String>,

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    @SerializedName("url")
    val url: String,

    @SerializedName("created")
    val created: String,

    @SerializedName("edited")
    val edited: String
)
