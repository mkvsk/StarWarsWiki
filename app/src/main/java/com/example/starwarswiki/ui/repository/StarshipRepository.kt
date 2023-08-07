package com.example.starwarswiki.ui.repository

import android.util.Log
import com.example.starwarswiki.callback.ResultCallback
import com.example.starwarswiki.network.RetrofitFactory
import com.example.starwarswiki.network.response.GetAllPeopleResponse
import com.example.starwarswiki.network.response.GetAllStarshipsResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class StarshipRepository {
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    companion object {
        const val TAG = "StarshipRepository"
    }

    fun getStarships(callback: ResultCallback<GetAllStarshipsResponse>) {
        scope.launch(Dispatchers.IO) {
            RetrofitFactory.apiService().getAllStarships()
                .enqueue(object : Callback<GetAllStarshipsResponse> {
                    override fun onResponse(
                        call: Call<GetAllStarshipsResponse>,
                        response: Response<GetAllStarshipsResponse>
                    ) {
                        if (response.code() == 200 || response.code() == 201) {
                            Log.d(
                                PersonRepository.TAG,
                                "GET STARSHIPS OK ${response.body().toString()}"
                            )
                            callback.onResult(response.body())
                        } else {
                            Log.d(
                                PersonRepository.TAG,
                                "GET STARSHIPS ERROR ${response.body().toString()}"
                            )
                            callback.onFailure(null)
                        }
                    }

                    override fun onFailure(call: Call<GetAllStarshipsResponse>, t: Throwable) {
                        Log.d(PersonRepository.TAG, "GET STARSHIPS EXCEPTION")
                        t.printStackTrace()
                        callback.onFailure(null)
                    }

                })
        }
    }

}