package com.example.tvcontrol.view

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tvcontrol.destionations.DeviceControl
import com.example.tvcontrol.destionations.Hello
import com.example.tvcontrol.destionations.Initial
import com.example.tvcontrol.destionations.Menu
import com.example.tvcontrol.destionations.Search
import com.example.tvcontrol.view.page.ControllerSetting
import com.example.tvcontrol.view.page.HelloScreen
import com.example.tvcontrol.view.page.RemoteControlPage
import com.example.tvcontrol.viewModels.DeviceViewModel
import com.example.tvcontrol.viewModels.TVControlViewModel
import com.google.gson.Gson
import kotlinx.coroutines.launch

@Composable
fun CustomNavHost(tvControlViewModel: TVControlViewModel, deviceViewModel: DeviceViewModel) {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()

    NavHost(navController = navController, startDestination = Initial) {
        composable<Initial> {
            InitialScreen(navController, deviceViewModel, tvControlViewModel)
        }
        composable<Hello> {
            HelloScreen {
                scope.launch {
                    navController.navigate(Search) {
                        popUpTo(Hello) {
                            inclusive = true
                        }
                    }
                }
            }
        }
        composable<Search> {
            val onDeviceConnected: () -> Unit = remember {
                {
                    scope.launch {
                        tvControlViewModel.currentDevice?.let {
                            navController.navigate("SaveDevice/${""}")
                        }
                    }
                }
            }
            SearchScreen(
                tvControlViewModel = tvControlViewModel,
                onDeviceConnected = onDeviceConnected
            )
        }
        composable(
            "DeviceControl/{deviceName}",
            arguments = listOf(navArgument("deviceName") { type = NavType.StringType })
        ) { backStackEntry ->
            val deviceName = remember {
                backStackEntry.arguments?.getString("deviceName")
            }
            tvControlViewModel.currentDevice?.let {
                RemoteControlPage(
                    deviceName = deviceName.toString(),
                    device = it,
                    onNavigateBack = {
                        scope.launch {
                            navController.popBackStack()
                        }
                    }
                )
            }
        }
        composable<Menu> {
            MenuScreen(
                tvControlViewModel = tvControlViewModel,
                deviceViewModel = deviceViewModel,
                navController = navController,
            )
        }
        composable("SaveDevice/{device}") { backStackEntry ->
            val deviceJson = backStackEntry.arguments?.getString("device")
            val device = remember {
                deviceJson?.let {
                    Gson().fromJson(
                        it,
                        com.example.tvcontrol.database.device.Device::class.java
                    )
                }
            }

            tvControlViewModel.currentDevice?.let {
                ControllerSetting(device = it,
                    savedDevice = device,
                    deviceViewModel = deviceViewModel,
                    onDeviceSaved = {
                        navController.navigate(Menu) {
                            popUpTo("SaveDevice/{device}") {
                                inclusive = true
                            }
                        }
                    },
                    onNavigateBack = {
                        scope.launch {
                            tvControlViewModel.resetCurrentDevice()
                            navController.popBackStack()
                        }
                    }
                )
            }
        }
    }
}