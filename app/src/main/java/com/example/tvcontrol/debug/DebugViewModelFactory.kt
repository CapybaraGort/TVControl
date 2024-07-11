package com.example.tvcontrol.debug

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DebugViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DebugViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DebugViewModel(app = app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}