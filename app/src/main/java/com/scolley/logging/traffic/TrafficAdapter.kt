package com.scolley.logging.traffic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scolley.logging.data.Information
import com.scolley.logging.databinding.ItemTrafficBinding

class TrafficAdapter(val viewModel: TrafficViewModel) :
        ListAdapter<Information, TrafficAdapter.InfoViewHolder>(DiffCallback){

    class InfoViewHolder(private var binding: ItemTrafficBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(info:Information , viewModel: TrafficViewModel){
            binding.info = info
            binding.executePendingBindings()
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<Information>() {
        override fun areItemsTheSame(oldItem: Information, newItem: Information): Boolean {
            return oldItem.chtmessage == newItem.chtmessage
        }

        override fun areContentsTheSame(oldItem: Information, newItem: Information): Boolean {
            return oldItem.content == newItem.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): InfoViewHolder {
        return InfoViewHolder(ItemTrafficBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        holder.bind(getItem(position), viewModel)
    }

}

