package com.example.starwarswiki.ui.view.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.starwarswiki.core.Film
import com.example.starwarswiki.core.Person
import com.example.starwarswiki.core.Planet
import com.example.starwarswiki.core.Starship
import com.example.starwarswiki.ui.view.listeners.OnAddRemoveFromFavListener
import online.example.starwarswiki.R
import online.example.starwarswiki.databinding.RvItemBinding

class HomeItemAdapter(private val context: Context) :
    RecyclerView.Adapter<HomeItemAdapter.ItemViewHolder>() {

    var inFav: Boolean = false
    private var data: List<Any> = mutableListOf()
    var favData: List<Any> = ArrayList()
    private lateinit var listener: OnAddRemoveFromFavListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val rvItem = data[position]
        holder.bind(rvItem, position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Any>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun checkIsFavourite(item: Any) {
        if (item is Person) {
            favData.forEach {
                when (it) {
                    is Person -> {
                        inFav = item.url == it.url
                    }

                    is Planet -> {
                        inFav = item.url == it.url
                    }

                    is Film -> {
                        inFav = item.url == it.url
                    }

                    is Starship -> {
                        inFav = item.url == it.url
                    }
                }
            }
        }

    }

    fun setClickListener(listener: OnAddRemoveFromFavListener) {
        this.listener = listener
    }

    inner class ItemViewHolder(rvItemBinding: RvItemBinding) :
        RecyclerView.ViewHolder(rvItemBinding.root) {
        private val binding = rvItemBinding

        fun bind(rvItem: Any, position: Int) {
            checkIsFavourite(rvItem)
            if (inFav) {
                Glide
                    .with(context)
                    .load(R.drawable.ic_in_fav)
                    .into(binding.btnAddRemove)
            } else {
                Glide
                    .with(context)
                    .load(R.drawable.ic_add_to_fav)
                    .into(binding.btnAddRemove)
            }

            when (rvItem) {
                is Person -> {
                    binding.tvItemType.text = context.getString(R.string.person)
                    binding.tvInfo.text =
                        String.format(
                            context.getString(R.string.format_person_info),
                            rvItem.name,
                            rvItem.gender,
                            rvItem.starships.count().toString()
                        )
                }

                is Starship -> {
                    binding.tvItemType.text = context.getString(R.string.starship)
                    binding.tvInfo.text =
                        String.format(
                            context.getString(R.string.format_starship_info),
                            rvItem.name,
                            rvItem.model,
                            rvItem.manufacturer,
                            rvItem.passengers.count().toString()
                        )
                }

                is Film -> {
                    binding.tvItemType.text = context.getString(R.string.film)
                    binding.tvInfo.text = String.format(
                        context.getString(R.string.format_film_info),
                        rvItem.title,
                        rvItem.director,
                        rvItem.producer
                    )
                }

                is Planet -> {
                    binding.tvItemType.text = context.getString(R.string.planet)
                    binding.tvInfo.text = String.format(
                        context.getString(R.string.format_planet_info),
                        rvItem.name,
                        rvItem.diameter,
                        rvItem.population
                    )
                }
            }

            binding.btnAddRemove.setOnClickListener {
                if (inFav) {
                    Glide
                        .with(context)
                        .load(R.drawable.ic_add_to_fav)
                        .into(binding.btnAddRemove)
                    listener.onItemRemoveFromFav(rvItem)
                } else {
                    Glide
                        .with(context)
                        .load(R.drawable.ic_in_fav)
                        .into(binding.btnAddRemove)
                    listener.onItemAddToFav(rvItem)
                }
            }
        }
    }
}
