package com.example.plantlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.plantlist.databinding.FragmentItemBinding

class MyItemRecyclerViewAdapter(
    private val plants: List<Plant>,
    private val onPlantClick: (Plant) -> Unit
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = plants[position]
        holder.bind(event)
        holder.itemView.setOnClickListener { onPlantClick(event) }
    }

    override fun getItemCount(): Int = plants.size

    inner class ViewHolder(private val binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(plant: Plant) {
            binding.plantName.text = plant.plantName
            binding.plantLatinName.text = plant.plantLatinName
        }
    }
}
