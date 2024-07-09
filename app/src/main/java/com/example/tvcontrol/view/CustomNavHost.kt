package com.example.tvcontrol.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tvcontrol.destionations.AddDevice
import com.example.tvcontrol.destionations.DeviceControl
import com.example.tvcontrol.destionations.Initial
import com.example.tvcontrol.destionations.ListOfDevices
import com.example.tvcontrol.destionations.Search
import com.example.tvcontrol.viewModels.DeviceViewModel
import com.example.tvcontrol.viewModels.TVControlViewModel
import kotlinx.coroutines.launch

@Composable
fun CustomNavHost(tvControlViewModel: TVControlViewModel, deviceViewModel: DeviceViewModel) {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()

    NavHost(navController = navController, startDestination = Initial) {
        composable<Initial> {
            InitialScreen(navController, deviceViewModel, tvControlViewModel)
        }
        composable<Search>{
            val onDeviceConnected: () -> Unit = remember {
                {
                    scope.launch {
                        tvControlViewModel.currentDevice?.let {
                            navController.navigate(AddDevice)
                        }
                    }
                }
            }
            ListOfFoundDevices(viewModel = tvControlViewModel,
                onDeviceConnected = onDeviceConnected)
        }
        composable<DeviceControl> {
            DeviceControlScreen(device = tvControlViewModel.currentDevice, onNavigateBack = {
                tvControlViewModel.resetCurrentDevice()
                navController.popBackStack()
            })
        }
        composable<ListOfDevices> {
            ListOfDevicesScreen(tvControlViewModel = tvControlViewModel,
                deviceViewModel = deviceViewModel
            ) { navController.navigate(DeviceControl) }
        }
        composable<AddDevice> {
            tvControlViewModel.currentDevice?.let {
                AddDeviceScreen(device = it, addDevice = deviceViewModel::insertDevice,
                    onDeviceAdded = {
                        navController.navigate(ListOfDevices) {
                            //TODO: test
                            popUpTo(Search) {
                                inclusive = true
                            }
                        }
                    })
            }
        }
    }
}