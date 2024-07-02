package com.example.tvcontrol

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TVControlViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TVControlViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TVControlViewModel(app = app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}