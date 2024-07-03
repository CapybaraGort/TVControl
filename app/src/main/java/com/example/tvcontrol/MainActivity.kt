package com.example.tvcontrol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.connectsdk.device.ConnectableDevice
import com.example.tvcontrol.destionations.DeviceControl
import com.example.tvcontrol.destionations.Search
import com.example.tvcontrol.ui.theme.AppTheme
import com.example.tvcontrol.view.DeviceControlScreen
import com.example.tvcontrol.view.ListOfFoundDevices

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val tvControlViewModel by viewModels<TVControlViewModel> { TVControlViewModelFactory(application) }

        setContent {
            AppTheme {
                CustomNavHost(tvControlViewModel)
            }
        }
    }

    @Composable
    private fun CustomNavHost(tvControlViewModel: TVControlViewModel) {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = Search) {
            composable<Search>{ ListOfFoundDevices(viewModel = tvControlViewModel,
                onDeviceConnected =  { navController.navigate(DeviceControl)})}
            composable<DeviceControl> {
                DeviceControlScreen(device = tvControlViewModel.currentDevice) }
        }
    }
}