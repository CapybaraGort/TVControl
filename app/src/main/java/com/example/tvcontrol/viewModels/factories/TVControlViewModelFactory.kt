package com.example.tvcontrol.viewModels.factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tvcontrol.viewModels.TVControlViewModel

class TVControlViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TVControlViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TVControlViewModel(app = app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}