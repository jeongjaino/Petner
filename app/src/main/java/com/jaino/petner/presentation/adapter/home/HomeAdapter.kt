package com.jaino.petner.presentation.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jaino.petner.databinding.ItemHomeBinding
import com.jaino.petner.domain.model.Schedule

class HomeAdapter : ListAdapter<Schedule, HomeAdapter.HomeViewHolder>(ScheduleCallback){

    companion object {
        val ScheduleCallback = object : DiffUtil.ItemCallback<Schedule>(){
            override fun areItemsTheSame(
                oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem.count == newItem.count
            }

            override fun areContentsTheSame(
                oldItem: Schedule,
                newItem: Schedule
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ))
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class HomeViewHolder(private val binding : ItemHomeBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Schedule) {
            binding.item = item
        }
    }
}