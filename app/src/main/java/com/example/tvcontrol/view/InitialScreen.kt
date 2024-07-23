package com.example.tvcontrol.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.tvcontrol.destionations.Hello
import com.example.tvcontrol.destionations.Menu
import com.example.tvcontrol.viewModels.DeviceViewModel
import com.example.tvcontrol.viewModels.TVControlViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeoutOrNull

@Composable
fun InitialScreen(
    navController: NavHostController,
    deviceViewModel: DeviceViewModel,
    tvControlViewModel: TVControlViewModel
) {
    var isLoading by remember { mutableStateOf(true) }
    val allDevices by deviceViewModel.allDevices.observeAsState()

    LaunchedEffect(Unit) {
        tvControlViewModel.startDiscovery()
        withTimeoutOrNull(200) {
            while (tvControlViewModel.uiState.value.devices.isEmpty()) {
                delay(100)
            }
        }
        val destination: Any = if (allDevices?.isEmpty() == true) Hello else Menu
        navController.navigate(destination) {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
            launchSingleTop = true
        }
        isLoading = false
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading)
            CircularProgressIndicator(modifier = Modifier.size(56.dp))
    }
}