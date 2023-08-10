package com.example.starwarswiki.network.response

import com.example.starwarswiki.core.Planet
import com.google.gson.annotations.SerializedName

class GetAllPlanetsResponse(
    @SerializedName("count")
    val count: Int,

    @SerializedName("next")
    val nextUrl: String?,

    @SerializedName("previous")
    val previousUrl: String?,

    @SerializedName("results")
    val results: ArrayList<Planet>
)
