package com.example.starwarswiki.ui.app

import android.app.Application
import com.example.starwarswiki.di.AppComponent
import com.example.starwarswiki.di.AppModule
import com.example.starwarswiki.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}