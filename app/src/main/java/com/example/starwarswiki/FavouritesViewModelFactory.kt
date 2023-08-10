package com.example.starwarswiki

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.starwarswiki.ui.local.FavouritesRepository
import com.example.starwarswiki.ui.viewmodel.FavouritesViewModel

class FavouritesViewModelFactory(
    private val repository: FavouritesRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FavouritesViewModel::class.java)){
            return FavouritesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}
