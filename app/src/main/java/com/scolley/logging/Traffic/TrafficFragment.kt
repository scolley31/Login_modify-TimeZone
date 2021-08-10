package com.scolley.logging.Traffic

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.scolley.logging.databinding.FragmentLoginBinding
import com.scolley.logging.databinding.FragmentTrafficBinding
import com.scolley.logging.login.LoginViewModel
import com.scolley.logging.login.LoginViewModelFactory

class TrafficFragment: Fragment() {

    private lateinit var trafficViewModel: TrafficViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        trafficViewModel = ViewModelProvider(this, TrafficViewModelFactory())
                .get(TrafficViewModel::class.java)

        val binding =  FragmentTrafficBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        trafficViewModel.info.observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("test","info = $it")
            }
        })

        return binding.root
    }
}