package com.example.starwarswiki.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarswiki.ui.view.adapters.ItemAdapter
import com.example.starwarswiki.ui.view.listeners.OnItemAddToFavListener
import online.example.starwarswiki.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), OnItemAddToFavListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var rv: RecyclerView? = null
    private var itemAdapter: ItemAdapter? = null
    private var data: ArrayList<Pair<Any, String>> = ArrayList()

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
        setupAdapter()
        initObservers()
        initViews()
    }

    private fun initViews() {
        data.add("Starship" to "1")
        data.add("Person" to "2")
        data.add("Starship" to "3")
        data.add("Starship" to "4")
        data.add("Person" to "5")
        data.add("Starship" to "6")
        data.add("Person" to "7")
        data.add("Person" to "8")
        data.add("Starship" to "9")
        data.add("Person" to "10")
        data.add("Starship" to "11")
        data.add("Person" to "12")

        itemAdapter?.setData(data)
    }

    private fun initObservers() {

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

    override fun onItemAddToFav(item: Pair<Any, String>, add: Boolean) {

    }
}