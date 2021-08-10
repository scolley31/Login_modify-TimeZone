package com.scolley.logging.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.scolley.logging.databinding.FragmentLoginBinding

class LoginFragment: Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
                .get(LoginViewModel::class.java)

        val binding =  FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = loginViewModel

        loginViewModel.username.observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("test","username = $it")
            }
        })

        loginViewModel.password.observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("test","password = $it")
            }
        })

        loginViewModel.user.observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("test","user = $it")
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToTrafficFragment())
            }
        })


        return binding.root
    }
}