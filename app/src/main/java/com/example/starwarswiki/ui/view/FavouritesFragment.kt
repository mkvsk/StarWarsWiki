package com.example.starwarswiki.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarswiki.ui.util.obtainViewModel
import com.example.starwarswiki.ui.view.adapters.FavItemAdapter
import com.example.starwarswiki.ui.view.adapters.HomeItemAdapter
import com.example.starwarswiki.ui.view.listeners.OnAddRemoveFromFavListener
import com.example.starwarswiki.ui.viewmodel.FavouritesViewModel
import kotlinx.coroutines.runBlocking
import online.example.starwarswiki.databinding.FragmentFavouritesBinding

class FavouritesFragment : Fragment(), OnAddRemoveFromFavListener {

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    private val favouritesViewModel by lazy { obtainViewModel(FavouritesViewModel::class.java) }
    private var rv: RecyclerView? = null
    private var favItemAdapter: FavItemAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        initObservers()
        initViews()
    }

    private fun initViews() {

    }

    private fun initObservers() {
        favouritesViewModel.fetchFavData().observe(viewLifecycleOwner, Observer {
            favItemAdapter!!.setData(it)
            Log.d("TAG", "initObservers: ${it.count()}")
            favItemAdapter!!.notifyDataSetChanged()
        })

    }

    private fun setupAdapter() {
        rv = binding.rvFavourites
        favItemAdapter = FavItemAdapter(requireContext())
        favItemAdapter!!.setClickListener(this)
        rv!!.adapter = favItemAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemAddToFav(item: Any) {

    }

    override fun onItemRemoveFromFav(item: Any) {
        runBlocking {
            favouritesViewModel.delete(item)
        }
    }

}