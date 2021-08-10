package com.scolley.logging.traffic

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.scolley.logging.databinding.FragmentTrafficBinding


class TrafficFragment: Fragment() {

    private lateinit var trafficViewModel: TrafficViewModel

    lateinit var binding: FragmentTrafficBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        trafficViewModel = ViewModelProvider(this, TrafficViewModelFactory())
                .get(TrafficViewModel::class.java)

        binding =  FragmentTrafficBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = trafficViewModel

        binding.RecyclerInfo.layoutManager = LinearLayoutManager(context)
        binding.RecyclerInfo.adapter = TrafficAdapter(trafficViewModel)

        trafficViewModel.info.observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("test","info = $it")
            }
        })

        return binding.root
    }
}