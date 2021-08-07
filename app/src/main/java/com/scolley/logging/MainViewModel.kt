package com.scolley.logging

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.scolley.logging.data.CurrentFragmentType
import com.scolley.logging.data.source.DefaultLoggingRepository
import com.scolley.logging.data.source.LoggingRepository

class MainViewModel(private val repository: LoggingRepository): ViewModel() {

    val currentFragmentType = MutableLiveData<CurrentFragmentType>()


}