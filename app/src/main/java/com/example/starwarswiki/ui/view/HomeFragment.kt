package com.example.starwarswiki.ui.view

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Parcelable
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
    private val dataTmp = mutableSetOf<Any>()
    private val dataFavTmp = mutableSetOf<Any>()

    companion object {
        const val KEY_RECYCLER_STATE = "recycler_state"
        const val TAG = "HomeFragment"
    }

    private var mBundleRecyclerViewState: Bundle? = null
    private var mListState: Parcelable? = null
    private var mRecyclerView: RecyclerView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView: ")
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
        binding.loader.progressOverlay.visibility = View.VISIBLE
        homeViewModel.getAllFilms()
        homeViewModel.getAllPeople()
        homeViewModel.getAllPlanets()
        homeViewModel.getAllStarships()

//        getData()
        setupAdapter()
        initObservers()
        initViews()
        initListeners()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach: ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
        _binding = null
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

        dataTmp.add(
            Film(
                title = "Film Allo",
                episodeId = 111,
                openingCrawl = "openingCrawl",
                director = "director",
                producer = "producer",
                releaseDate = "releaseDate",
                species = emptyList(),
                starships = emptyList(),
                vehicles = emptyList(),
                url = "url1339",
                created = "created",
                edited = "edited",
                characters = emptyList(),
                planets = emptyList()
            )
        )
        dataTmp.add(
            Film(
                title = "Film bla bla",
                episodeId = 222,
                openingCrawl = "openingCrawl",
                director = "director",
                producer = "producer",
                releaseDate = "releaseDate",
                species = emptyList(),
                starships = emptyList(),
                vehicles = emptyList(),
                url = "url1337",
                created = "created",
                edited = "edited",
                characters = emptyList(),
                planets = emptyList()
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
        }.toMutableSet()

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
        homeViewModel.allPeople.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                dataTmp.addAll(it)
                homeItemAdapter?.setData(dataTmp)
                binding.loader.progressOverlay.visibility = View.GONE
            }
        })

        homeViewModel.allPlanets.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                dataTmp.addAll(it)
            }
        })

        homeViewModel.allStarships.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                dataTmp.addAll(it)
            }
        })

        homeViewModel.allFilms.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                dataTmp.addAll(it)
            }
        })

        favouritesViewModel.fetchFavPeople().observe(viewLifecycleOwner, Observer {
            it.forEach { person ->
                dataFavTmp.add(person)
            }
            homeItemAdapter?.setFavData(dataFavTmp)
            homeItemAdapter?.setData(dataTmp)
            Log.d("TAG", "fetch PERSON: ${it.count()}")
            homeItemAdapter!!.notifyDataSetChanged()

        })

        favouritesViewModel.fetchFavFilms().observe(viewLifecycleOwner, Observer {
            it.forEach { film ->
                dataFavTmp.add(film)
            }
            homeItemAdapter?.setFavData(dataFavTmp)
            homeItemAdapter?.setData(dataTmp)

            Log.d("TAG", "fetch FILMS: ${it.count()}")
            homeItemAdapter!!.notifyDataSetChanged()

        })

        favouritesViewModel.fetchFavPlanets().observe(viewLifecycleOwner, Observer {
            it.forEach { planet ->
                dataFavTmp.add(planet)
            }
            homeItemAdapter?.setFavData(dataFavTmp)
            homeItemAdapter?.setData(dataTmp)

            Log.d("TAG", "fetch PLANETS: ${it.count()}")
            homeItemAdapter!!.notifyDataSetChanged()

        })

        favouritesViewModel.fetchFavStarships().observe(viewLifecycleOwner, Observer {
            it.forEach { starship ->
                dataFavTmp.add(starship)
            }
            homeItemAdapter?.setFavData(dataFavTmp)
            homeItemAdapter?.setData(dataTmp)

            Log.d("TAG", "fetch STARSHIPS: ${it.count()}")
            homeItemAdapter!!.notifyDataSetChanged()
        })

    }

    private fun setupAdapter() {
        rv = binding.rvHome
        mRecyclerView = rv
        homeItemAdapter = HomeItemAdapter(requireContext())
        homeItemAdapter!!.setClickListener(this)
        rv?.adapter = homeItemAdapter
        rv?.adapter?.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView: ")
    }

    override fun onItemAddToFav(item: Any, position: Int) {
        favouritesViewModel.insert(item)
        homeItemAdapter!!.notifyItemChanged(position)
    }

    override fun onItemRemoveFromFav(item: Any, position: Int) {
        favouritesViewModel.delete(item)
        homeItemAdapter!!.notifyItemChanged(position)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
        if (mBundleRecyclerViewState != null) {
            Looper.myLooper()?.let {
                Handler(it).post {
                    mListState = mBundleRecyclerViewState?.getBundle(KEY_RECYCLER_STATE)
                    mRecyclerView!!.layoutManager?.onRestoreInstanceState(mListState)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
        mBundleRecyclerViewState = Bundle()
        mListState = mRecyclerView!!.layoutManager?.onSaveInstanceState()
        mBundleRecyclerViewState?.putParcelable(KEY_RECYCLER_STATE, mListState)
    }
}