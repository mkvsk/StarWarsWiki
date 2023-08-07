package com.example.starwarswiki

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.example.starwarswiki.ui.local.FavouritesDatabase
import com.example.starwarswiki.ui.local.FavouritesDao
import com.example.starwarswiki.ui.local.FavouritesRepository
import com.example.starwarswiki.ui.viewmodel.FavouritesViewModel
import com.example.starwarswiki.ui.viewmodel.HomeViewModel
import online.example.starwarswiki.R
import online.example.starwarswiki.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var favouritesViewModel: FavouritesViewModel
    private lateinit var favouritesDao: FavouritesDao

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instantiateViewModels()
        setupRoom()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)
        navView.setOnItemSelectedListener {
            NavigationUI.onNavDestinationSelected(it, navController)
            return@setOnItemSelectedListener true
        }

    }

    private fun setupRoom() {
        favouritesDao = FavouritesDatabase.getInstance(application).favouritesDao
        val repository = FavouritesRepository(favouritesDao)

    }

    private fun instantiateViewModels() {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        favouritesViewModel = ViewModelProvider(this)[FavouritesViewModel::class.java]
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}