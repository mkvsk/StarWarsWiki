package com.example.starwarswiki.ui.repository

import android.util.Log
import com.example.starwarswiki.callback.ResultCallback
import com.example.starwarswiki.network.RetrofitFactory
import com.example.starwarswiki.network.response.GetAllPlanetsResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class PlanetRepository {
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    companion object {
        const val TAG = "PlanetRepository"
    }

    fun getPlanets(callback: ResultCallback<GetAllPlanetsResponse>) {
        scope.launch(Dispatchers.IO) {
            RetrofitFactory.apiService().getAllPlanets()
                .enqueue(object : Callback<GetAllPlanetsResponse> {
                    override fun onResponse(
                        call: Call<GetAllPlanetsResponse>,
                        response: Response<GetAllPlanetsResponse>
                    ) {
                        if (response.code() == 200 || response.code() == 201) {
                            Log.d(TAG, "GET PLANETS OK ${response.body().toString()}")
                            callback.onResult(response.body())
                        } else {
                            Log.d(TAG, "GET PLANETS ERROR ${response.raw().toString()}")
                            callback.onFailure(null)
                        }
                    }

                    override fun onFailure(call: Call<GetAllPlanetsResponse>, t: Throwable) {
                        Log.d(TAG, "GET PLANETS EXCEPTION")
                        t.printStackTrace()
                        callback.onFailure(null)
                    }

                })
        }
    }
}