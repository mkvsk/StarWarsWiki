package com.example.starwarswiki.ui.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarswiki.core.Film
import com.example.starwarswiki.core.Person
import com.example.starwarswiki.core.Planet
import com.example.starwarswiki.core.Starship
import com.example.starwarswiki.ui.util.hideKeyboard
import com.example.starwarswiki.ui.util.obtainViewModel
import com.example.starwarswiki.ui.view.adapters.HomeItemAdapter
import com.example.starwarswiki.ui.view.listeners.OnAddRemoveFromFavListener
import com.example.starwarswiki.ui.viewmodel.FavouritesViewModel
import com.example.starwarswiki.ui.viewmodel.HomeViewModel
import online.example.starwarswiki.databinding.FragmentHomeBinding


class HomeFragment : Fragment(), OnAddRemoveFromFavListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel by lazy { obtainViewModel(HomeViewModel::class.java) }
    private val favouritesViewModel by lazy { obtainViewModel(FavouritesViewModel::class.java) }

    private var rv: RecyclerView? = null
    private var homeItemAdapter: HomeItemAdapter? = null
    private val dataTmp = ArrayList<Any>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        favouritesViewModel.fetchFavData()
//        homeViewModel.getAllFilms()
//        homeViewModel.getAllPeople()
//        homeViewModel.getAllPlanets()
//        homeViewModel.getAllStarships()
        getData()
        setupAdapter()
        initObservers()
        initViews()
        initListeners()
    }

    private fun getData() {
        repeat(5) {
            dataTmp.add(
                Person(
                    name = "name ${it.inc()}",
                    birthYear = "birthYear",
                    height = "height",
                    mass = "mass",
                    skinColor = "skinColor",
                    homeWorld = "homeWorld",
                    films = emptyList(),
                    species = emptyList(),
                    starships = emptyList(),
                    vehicles = emptyList(),
                    url = "url${it.inc()}",
                    created = "created",
                    edited = "edited"
                )
            )
        }
        dataTmp.add(
            Person(
                name = "Allo",
                birthYear = "birthYear",
                height = "height",
                mass = "mass",
                skinColor = "skinColor",
                homeWorld = "homeWorld",
                films = emptyList(),
                species = emptyList(),
                starships = emptyList(),
                vehicles = emptyList(),
                url = "url1339",
                created = "created",
                edited = "edited"
            )
        )

    }

    private fun initListeners() {
        binding.edittextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(queryText: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!binding.edittextSearch.text.isNullOrBlank() && binding.edittextSearch.text.toString().length > 1) {
                    filter(binding.edittextSearch.text.toString())
//                    if (queryText.length >= 2) {
//                        val temp = homeViewModel.allData.value!!.filter {
//                            when (it) {
//                                is Person -> {
//                                    it.name.contains(queryText.toString(), true)
//                                }
//
//                                is Planet -> {
//                                    it.name.contains(queryText.toString(), true)
//                                }
//
//                                is Film -> {
//                                    it.title.contains(queryText.toString(), true)
//                                }
//
//                                is Starship -> {
//                                    it.name.contains(queryText.toString(), true)
////                                    or it . model . contains (queryText.toString(), true)
//                                }
//
//                                else -> {
//                                    it.toString().contains(queryText.toString(), true)
//                                }
//                            }
//                        }
//                        Log.d("TAG", "filter: ${temp.joinToString("*")}")
//                        homeItemAdapter!!.setData(temp)
//                    }
                } else {
                    homeItemAdapter!!.setData(dataTmp)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        binding.edittextSearch.setOnEditorActionListener { _, keyCode, event ->
            if (((event?.action ?: -1) == KeyEvent.KEYCODE_ENTER)
                || keyCode == EditorInfo.IME_ACTION_DONE
            ) {
                hideKeyboard(binding.edittextSearch)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun filter(s: String) {
        s.trim()
        val temp = dataTmp.filter {
            when (it) {
                is Person -> {
                    it.name.contains(s, true)
                }

                is Planet -> {
                    it.name.contains(s, true)
                }

                is Film -> {
                    it.title.contains(s, true)
                }

                is Starship -> {
                    it.name.contains(s, true) or it.model.contains(s, true)
                }

                else -> {
                    it.toString().contains(s, true)
                }
            }
        }

        homeItemAdapter.run {
            if (temp.isNotEmpty()) {
                this!!.setData(temp)
            } else {
                this!!.setData(dataTmp)
            }
        }
    }

    private fun initViews() {

    }

    private fun initObservers() {
        favouritesViewModel.fetchFavData().observe(viewLifecycleOwner, Observer {
            homeItemAdapter?.favData = it
            homeItemAdapter?.setData(dataTmp)
            Log.d("TAG", "initObservers: ${it.count()}")

        })

    }

    private fun setupAdapter() {
        rv = binding.rvHome
        homeItemAdapter = HomeItemAdapter(requireContext())
        homeItemAdapter!!.setClickListener(this)
        rv!!.adapter = homeItemAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemAddToFav(item: Any) {
        favouritesViewModel.insert(item)
    }

    override fun onItemRemoveFromFav(item: Any) {
        favouritesViewModel.delete(item)
    }
}