package com.example.tvcontrol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.tvcontrol.database.DeviceViewModel
import com.example.tvcontrol.ui.theme.AppTheme
import com.example.tvcontrol.view.CustomNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val tvControlViewModel by viewModels<TVControlViewModel> { TVControlViewModelFactory(application) }
        val deviceViewModel by viewModels<DeviceViewModel> { DeviceViewModelFactory(application) }
        setContent {
            AppTheme {
                CustomNavHost(tvControlViewModel, deviceViewModel)
            }
        }
    }
}