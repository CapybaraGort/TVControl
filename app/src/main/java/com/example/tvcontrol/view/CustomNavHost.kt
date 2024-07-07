package com.example.tvcontrol.view

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tvcontrol.TVControlViewModel
import com.example.tvcontrol.database.DeviceViewModel
import com.example.tvcontrol.destionations.DeviceControl
import com.example.tvcontrol.destionations.ListOfDevices
import com.example.tvcontrol.destionations.Initial
import com.example.tvcontrol.destionations.Search
import kotlinx.coroutines.launch

@Composable
fun CustomNavHost(tvControlViewModel: TVControlViewModel, deviceViewModel: DeviceViewModel) {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val toast = Toast(LocalContext.current)

    NavHost(navController = navController, startDestination = Initial) {
        composable<Initial> {
            InitialScreen(navController, deviceViewModel, tvControlViewModel)
        }
        composable<Search>{
            ListOfFoundDevices(viewModel = tvControlViewModel,
                onDeviceConnected =  {
                    scope.launch {
                        tvControlViewModel.currentDevice?.let {
                            if (deviceViewModel.existsDevice(it)) {
                                toast.setText("уже есть")
                                toast.show()
                            }
                            else {
                                deviceViewModel.insertDevice(it)
                            }
                            navController.navigate(DeviceControl)
                        }
                    }
                })
        }
        composable<DeviceControl> {
            DeviceControlScreen(device = tvControlViewModel.currentDevice, onNavigateBack = navController::popBackStack)
        }
        composable<ListOfDevices> {
            ListOfDevicesScreen(tvControlViewModel = tvControlViewModel,
                deviceViewModel = deviceViewModel
            ) { navController.navigate(DeviceControl) }
        }
    }
}