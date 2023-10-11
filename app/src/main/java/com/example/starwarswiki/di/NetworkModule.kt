package com.example.starwarswiki.di

import com.example.starwarswiki.network.NetworkInfoService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import online.example.starwarswiki.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    private val gson = GsonBuilder().setLenient().create()
    private val baseURL = BuildConfig.API_URL

    @Singleton
    @Provides
    fun provideApiService(): NetworkInfoService {
        return Retrofit.Builder().baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
            .create(NetworkInfoService::class.java)
    }
}