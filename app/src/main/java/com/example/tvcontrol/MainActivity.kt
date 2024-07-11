package com.example.tvcontrol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.tvcontrol.viewModels.DeviceViewModel
import com.example.tvcontrol.ui.theme.AppTheme
import com.example.tvcontrol.debug.DebugScreen
import com.example.tvcontrol.debug.DebugViewModel
import com.example.tvcontrol.viewModels.TVControlViewModel
import com.example.tvcontrol.debug.DebugViewModelFactory
import com.example.tvcontrol.view.CustomNavHost
import com.example.tvcontrol.viewModels.factories.DeviceViewModelFactory
import com.example.tvcontrol.viewModels.factories.TVControlViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val tvControlViewModel by viewModels<TVControlViewModel> { TVControlViewModelFactory(application) }
        val deviceViewModel by viewModels<DeviceViewModel> { DeviceViewModelFactory(application) }
        val debugViewModel by viewModels<DebugViewModel> { DebugViewModelFactory(application) }
        //debugViewModel.startDiscovery()
        deviceViewModel.deleteAllDevices()
        setContent {
            AppTheme {
                //DebugScreen(debugViewModel = debugViewModel)
                CustomNavHost(tvControlViewModel, deviceViewModel)
            }
        }
    }
}