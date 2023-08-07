package com.example.starwarswiki.ui.view.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.starwarswiki.core.Person
import com.example.starwarswiki.core.Starship
import com.example.starwarswiki.ui.view.listeners.OnItemAddToFavListener
import online.example.starwarswiki.R
import online.example.starwarswiki.databinding.RvItemBinding

class ItemAdapter(private val context: Context) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private var data: ArrayList<Any> = ArrayList()
    private lateinit var listener: OnItemAddToFavListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val rvItem = data[position]
        holder.bind(rvItem)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: ArrayList<Any>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun setClickListener(listener: OnItemAddToFavListener) {
        this.listener = listener
    }

    inner class ItemViewHolder(rvItemBinding: RvItemBinding) :
        RecyclerView.ViewHolder(rvItemBinding.root) {
        private val binding = rvItemBinding

        fun bind(rvItem: Any) {
            if (rvItem is Person) {
                binding.tvItemType.text = "Person"
                binding.tvTitle.text = rvItem.name
            } else if (rvItem is Starship) {
                binding.tvItemType.text = "Starship"
                binding.tvTitle.text = rvItem.name
            }

            binding.btnSetFav.setOnClickListener {
                Glide
                    .with(context)
                    .load(R.drawable.ic_fav)
                    .into(binding.btnSetFav)
                listener.onItemAddToFav(rvItem, true)
            }

        }
    }
}
