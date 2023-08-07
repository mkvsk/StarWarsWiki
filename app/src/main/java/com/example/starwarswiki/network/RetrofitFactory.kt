package com.example.starwarswiki.network

import com.google.gson.GsonBuilder
import online.example.starwarswiki.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    private val gson = GsonBuilder().setLenient().create()
    private const val URL = BuildConfig.API_URL

    fun apiService(): NetworkInfoService {
        return Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
            .create(NetworkInfoService::class.java)
    }
}