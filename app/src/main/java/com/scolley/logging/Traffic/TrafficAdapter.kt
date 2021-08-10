package com.scolley.logging.Traffic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scolley.logging.data.Information
import com.scolley.logging.databinding.ItemTrafficBinding

class TrafficAdapter(val viewModel: TrafficViewModel) :
        ListAdapter<Information, RecyclerView.ViewHolder>(DiffCallback){

    class InfoViewHolder(private var binding: ItemTrafficBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(info:Information , viewModel: TrafficViewModel){
            binding.right = user
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): UserViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemOverviewBinding.inflate(layoutInflater, parent, false)
                return UserViewHolder(binding)
            }
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_ONE -> UserViewHolder.from(parent)
            ITEM_VIEW_TYPE_TWO -> UserOtherTypeViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder) {
            is UserViewHolder -> {
                holder.bind(getItem(position) as DataItem.Right, viewModel)
            }
            is UserOtherTypeViewHolder -> {
                holder.bind(getItem(position) as DataItem.Left, viewModel)
            }
        }
    }

}

