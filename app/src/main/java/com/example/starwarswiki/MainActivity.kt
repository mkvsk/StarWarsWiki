package com.example.starwarswiki

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.starwarswiki.ui.app.App
import com.example.starwarswiki.ui.local.FavouritesDao
import com.example.starwarswiki.ui.local.FavouritesDatabase
import com.example.starwarswiki.ui.local.FavouritesRepository
import com.example.starwarswiki.ui.viewmodel.FavouritesViewModel
import com.example.starwarswiki.ui.viewmodel.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import online.example.starwarswiki.R
import online.example.starwarswiki.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var favouritesViewModel: FavouritesViewModel

    @Inject
     lateinit var favouritesDao: FavouritesDao

    @Inject
     lateinit var favouritesRepository: FavouritesRepository

    @Inject
    lateinit var favouritesFactory: FavouritesViewModelFactory

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: MAIN CREATE")

        instantiateViewModels()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (applicationContext as App).appComponent.inject(this)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)
        navView.setOnItemSelectedListener {
            NavigationUI.onNavDestinationSelected(it, navController)
            return@setOnItemSelectedListener true
        }
    }

    private fun instantiateViewModels() {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        favouritesDao = FavouritesDatabase.getInstance(application).favouritesDao()
        favouritesRepository = FavouritesRepository(favouritesDao)
        favouritesFactory = FavouritesViewModelFactory(favouritesRepository)
        favouritesViewModel =
            ViewModelProvider(this, favouritesFactory)[FavouritesViewModel::class.java]
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        Log.d(TAG, "onDestroy: MAIN DESTROY")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: MAIN START")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: MAIN RESUME")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: MAIN PAUSE")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: MAIN STOP")
    }

}