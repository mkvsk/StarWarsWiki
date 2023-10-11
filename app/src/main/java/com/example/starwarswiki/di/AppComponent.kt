package com.example.starwarswiki.di

import com.example.starwarswiki.MainActivity
import dagger.Component

@Component(modules = [AppModule::class, DataModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}