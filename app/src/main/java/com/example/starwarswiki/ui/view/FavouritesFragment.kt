package com.example.starwarswiki.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarswiki.ui.util.obtainViewModel
import com.example.starwarswiki.ui.view.adapters.ItemAdapter
import com.example.starwarswiki.ui.view.listeners.OnItemAddToFavListener
import com.example.starwarswiki.ui.viewmodel.FavouritesViewModel
import online.example.starwarswiki.databinding.FragmentFavouritesBinding

class FavouritesFragment : Fragment(), OnItemAddToFavListener {

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    private val favouritesViewModel by lazy { obtainViewModel(FavouritesViewModel::class.java) }

    private var rv: RecyclerView? = null
    private var itemAdapter: ItemAdapter? = null

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
        favouritesViewModel.setAllPeople()
        setupAdapter()
        initObservers()
        initViews()

    }

    private fun initViews() {

    }

    private fun initObservers() {
        favouritesViewModel.allPeople.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                Log.d(
                    "TAG",
                    "initObservers: ${favouritesViewModel.allPeople.value?.joinToString(",")}"
                )
            } else {
                Log.d("TAG", "initObservers: NULL")
            }
        }
    }

    private fun setupAdapter() {
        rv = binding.rvFavourites
        itemAdapter = ItemAdapter(requireContext())
        itemAdapter!!.setClickListener(this)
        rv!!.adapter = itemAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemAddToFav(item: Any, add: Boolean) {

    }

}