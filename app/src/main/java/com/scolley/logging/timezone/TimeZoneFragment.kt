package com.scolley.logging.timezone

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.scolley.logging.databinding.FragmentLoginBinding
import com.scolley.logging.databinding.FragmentTimezoneBinding
import com.scolley.logging.login.LoginViewModel
import com.scolley.logging.login.LoginViewModelFactory

class TimeZoneFragment: Fragment() {

    private lateinit var timeZoneViewModel: TimeZoneViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        timeZoneViewModel = ViewModelProvider(this, TimeZoneViewModelFactory())
                .get(TimeZoneViewModel::class.java)

        val binding =  FragmentTimezoneBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = timeZoneViewModel

        timeZoneViewModel.userEmail.observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("test","userEmail = $it")
            }
        })

        return binding.root
    }


}