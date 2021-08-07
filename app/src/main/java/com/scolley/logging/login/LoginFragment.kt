package com.scolley.logging.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.scolley.logging.LoggingApplication
import com.scolley.logging.ext.getVmFactory
import com.scolley.logging.databinding.FragmentLoginBinding
import com.scolley.logging.factory.ViewModelFactory

class LoginFragment: Fragment() {

    private val viewModel by viewModels<LoginViewModel>
    { ViewModelFactory(LoggingApplication.instance.loggingRepository) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding =  FragmentLoginBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        return binding.root
    }
}