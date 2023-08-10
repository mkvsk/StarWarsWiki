package com.example.starwarswiki.ui.view.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarswiki.core.Film
import com.example.starwarswiki.core.Person
import com.example.starwarswiki.core.Planet
import com.example.starwarswiki.core.Starship
import com.example.starwarswiki.ui.view.listeners.OnAddRemoveFromFavListener
import online.example.starwarswiki.R
import online.example.starwarswiki.databinding.RvItemBinding

class HomeItemAdapter(private val context: Context) :
    RecyclerView.Adapter<HomeItemAdapter.ItemViewHolder>() {
    private var inFav = false
    private var data = mutableSetOf<Any>()
    private var favData = mutableSetOf<Any>()
    private lateinit var listener: OnAddRemoveFromFavListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val rvItem = data.elementAt(position)
        holder.bind(rvItem)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: MutableSet<Any>) {
        this.data = data
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setFavData(favData: MutableSet<Any>) {
        this.favData = favData
    }

    fun checkIsFavourite(item: Any) {
        inFav = favData.contains(item)

//        favData.forEach {
//            when (it) {
//                is Person -> {
//                    if (item == it) {
//                        inFav = true
//                    } else {
//                        inFav = false
//                    }
////                    inFav = item == it
//                }
//
//                is Planet -> {
//                    if (item == it) {
//                        inFav = true
//                    } else {
//                        inFav = false
//                    }
////                    inFav = item == it
//
//                }
//
//                is Film -> {
//                    if (item == it) {
//                        inFav = true
//                    } else {
//                        inFav = false
//                    }
////                    inFav = item == it
//                }
//
//                is Starship -> {
//                    if (item == it) {
//                        inFav = true
//                    } else {
//                        inFav = false
//                    }
////                    inFav = item == it
//                }
//            }
//        }


    }

    fun setClickListener(listener: OnAddRemoveFromFavListener) {
        this.listener = listener
    }

    inner class ItemViewHolder(rvItemBinding: RvItemBinding) :
        RecyclerView.ViewHolder(rvItemBinding.root) {
        private val binding = rvItemBinding

        fun bind(rvItem: Any) {
            inFav = false
            favData.forEach {
                if (rvItem == it) {
                    inFav = true
                }
            }
            toggleFavourites(binding.btnAddRemove, inFav)
//            checkIsFavourite(rvItem)
//            if (inFav) {
//                Glide
//                    .with(context)
//                    .load(R.drawable.ic_in_fav)
//                    .into(binding.btnAddRemove)
//            } else {
//                Glide
//                    .with(context)
//                    .load(R.drawable.ic_add_to_fav)
//                    .into(binding.btnAddRemove)
//            }

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
                    listener.onItemRemoveFromFav(rvItem, bindingAdapterPosition)
                } else {
                    listener.onItemAddToFav(rvItem, bindingAdapterPosition)
                }
            }
        }
    }

    private fun toggleFavourites(favourite: ImageView, inFav: Boolean) {
        if (inFav) {
            favourite.setImageResource(R.drawable.ic_in_fav)
        } else {
            favourite.setImageResource(R.drawable.ic_add_to_fav)
        }
    }
}
