package com.example.starwarswiki.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarswiki.ui.util.obtainViewModel
import com.example.starwarswiki.ui.view.adapters.ItemAdapter
import com.example.starwarswiki.ui.view.listeners.OnItemAddToFavListener
import com.example.starwarswiki.ui.viewmodel.HomeViewModel
import online.example.starwarswiki.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), OnItemAddToFavListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel by lazy { obtainViewModel(HomeViewModel::class.java) }


    private var rv: RecyclerView? = null
    private var itemAdapter: ItemAdapter? = null

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
        homeViewModel.getAllPeople()
        homeViewModel.getAllStarships()
        setupAdapter()
        initObservers()
        initViews()
    }

    private fun initViews() {

    }

    private fun initObservers() {
        homeViewModel.allPeople.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty() && !homeViewModel.allStarships.value.isNullOrEmpty()) {
                homeViewModel.getAllData()
            }
        }

        homeViewModel.allData.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                itemAdapter?.setData(it)
            }
        }
    }

    private fun setupAdapter() {
        rv = binding.rvHome
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