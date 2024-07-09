package com.example.tvcontrol.viewModels.factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tvcontrol.viewModels.DeviceViewModel

class DeviceViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DeviceViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DeviceViewModel(application = app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
