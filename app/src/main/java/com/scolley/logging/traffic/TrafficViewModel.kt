package com.scolley.logging.traffic

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.scolley.logging.data.Information
import com.scolley.logging.data.Result
import com.scolley.logging.data.source.LoggingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TrafficViewModel(private val loggingRepository: LoggingRepository): ViewModel() {

    private val _info = MutableLiveData<List<Information>>()

    val info: LiveData<List<Information>>
        get() = _info

    private val _navigateToTimeZone = MutableLiveData<Boolean>()

    val navigateToTimeZone: MutableLiveData<Boolean>
        get() = _navigateToTimeZone

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    init {
        getTrafficNews()
    }

    private fun getTrafficNews() {

        coroutineScope.launch {

            val result = loggingRepository.getTrafficNews()
            Log.d("test","result = $result")

            _info.value = when (result) {
                is Result.Success -> {
                    result.data.News
                }
                is Result.Fail -> {
                    null
                }
                is Result.Error -> {
                    null
                }
                else ->{
                    null
                }
            }

        }
    }

    fun navigateTimeZone() {
        _navigateToTimeZone.value = true
    }


    fun deleteNavigateTimeZone() {
        _navigateToTimeZone.value = null
    }

}