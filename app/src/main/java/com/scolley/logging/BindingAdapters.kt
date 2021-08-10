package com.scolley.logging

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scolley.logging.traffic.TrafficAdapter
import com.scolley.logging.data.Information

@BindingAdapter("infos")
fun bindRecyclerViewWithInfo(recyclerView: RecyclerView, info: List<Information>?) {
    info?.let {
        recyclerView.adapter?.apply {
            when (this) {
                is TrafficAdapter -> {
                    notifyDataSetChanged()
                    submitList(it)
                }
            }
        }
    }
}