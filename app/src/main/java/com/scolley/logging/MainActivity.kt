package com.scolley.logging

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.scolley.logging.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//    val viewModel by viewModels<MainViewModel> { getVmFactory() }

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

    }

}

//private fun setupNavController() {
//    findNavController(R.id.myNavHostFragment).addOnDestinationChangedListener { navController: NavController, _: NavDestination, _: Bundle? ->
//        viewModel.currentFragmentType.value = when (navController.currentDestination?.id) {
//            R.id.homeFragment -> CurrentFragmentType.HOME
//            R.id.otherFragment -> CurrentFragmentType.OTHER
//            R.id.timerFragment -> CurrentFragmentType.TIMER
//            R.id.loginFragment -> CurrentFragmentType.LOGIN
//
//            else -> viewModel.currentFragmentType.value
//        }
//    }
//}