package com.scolley.logging.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
                loginViewModel.clickEditText(true)
            }
        })

        loginViewModel.password.observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("test","password = $it")
                loginViewModel.clickEditText(false)
            }
        })

        loginViewModel.user.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(this.requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
                Log.d("test","user = $it")
                UserManager.user.value = loginViewModel.user.value
                UserManager.isLoggedIn.value = true
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToTrafficFragment())
                loginViewModel.clearLiveData()
            }
        })

        loginViewModel.loginTest.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(this.requireContext(), "Login Fail", Toast.LENGTH_SHORT).show()
                Log.d("test","loginTest = $it")
            }
        })

        binding.login.setOnClickListener {
            loginViewModel.loginAttempt()
        }


        return binding.root
    }
}