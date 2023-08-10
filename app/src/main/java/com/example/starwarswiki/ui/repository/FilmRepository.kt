package com.example.starwarswiki.ui.repository

import android.util.Log
import com.example.starwarswiki.callback.ResultCallback
import com.example.starwarswiki.network.RetrofitFactory
import com.example.starwarswiki.network.response.GetAllFilmsResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class FilmRepository {
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    companion object {
        const val TAG = "FilmRepository"
    }

    fun getFilms(callback: ResultCallback<GetAllFilmsResponse>) {
        scope.launch(Dispatchers.IO) {
            RetrofitFactory.apiService().getAllFilms()
                .enqueue(object : Callback<GetAllFilmsResponse> {
                    override fun onResponse(
                        call: Call<GetAllFilmsResponse>,
                        response: Response<GetAllFilmsResponse>
                    ) {
                        if (response.code() == 200 || response.code() == 201) {
                            Log.d(TAG, "GET FILMS OK ${response.body().toString()}")
                            callback.onResult(response.body())
                        } else {
                            Log.d(TAG, "GET FILMS ERROR ${response.raw().toString()}")
                            callback.onFailure(null)
                        }
                    }

                    override fun onFailure(call: Call<GetAllFilmsResponse>, t: Throwable) {
                        Log.d(TAG, "GET FILMS EXCEPTION")
                        t.printStackTrace()
                        callback.onFailure(null)
                    }

                })
        }
    }
}