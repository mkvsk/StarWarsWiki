package com.example.starwarswiki.ui.repository

import android.util.Log
import com.example.starwarswiki.callback.ResultCallback
import com.example.starwarswiki.network.RetrofitFactory
import com.example.starwarswiki.network.response.GetAllPeopleResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class PersonRepository {
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    companion object {
        const val TAG = "PersonRepository"
    }

    fun getPeople(callback: ResultCallback<GetAllPeopleResponse>) {
        scope.launch(Dispatchers.IO) {
            RetrofitFactory.apiService().getAllPeople()
                .enqueue(object : Callback<GetAllPeopleResponse> {
                    override fun onResponse(
                        call: Call<GetAllPeopleResponse>,
                        response: Response<GetAllPeopleResponse>
                    ) {
                        if (response.code() == 200 || response.code() == 201) {
                            Log.d(TAG, "GET PEOPLE OK ${response.body().toString()}")
                            callback.onResult(response.body())
                        } else {
                            Log.d(TAG, "GET PEOPLE ERROR ${response.raw().toString()}")
                            callback.onFailure(null)
                        }
                    }

                    override fun onFailure(call: Call<GetAllPeopleResponse>, t: Throwable) {
                        Log.d(TAG, "GET PEOPLE EXCEPTION")
                        t.printStackTrace()
                        callback.onFailure(null)
                    }

                })
        }
    }
}