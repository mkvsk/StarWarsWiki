package com.example.starwarswiki.network.response

import com.example.starwarswiki.core.Film
import com.google.gson.annotations.SerializedName

class GetAllFilmsResponse(
    @SerializedName("count")
    val count: Int,

    @SerializedName("next")
    val nextUrl: String?,

    @SerializedName("previous")
    val previousUrl: String?,

    @SerializedName("results")
    val results: ArrayList<Film>
)
