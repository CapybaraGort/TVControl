package com.example.tvcontrol.debug

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val localParams = compositionLocalOf<DebugParams> {
    error("not")
}

@Composable
fun DebugScreen(modifier: Modifier = Modifier, debugViewModel: DebugViewModel) {
    val debugParams = DebugParams(debugViewModel = debugViewModel)
    CompositionLocalProvider(value = localParams provides debugParams) {
        Scaffold(modifier = modifier.windowInsetsPadding(WindowInsets.systemBars)) { innerPadding ->
            Column(modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Devices()
            }
        }
    }
}

@Composable
private fun Devices(modifier: Modifier = Modifier) {
    val debugViewModel = localParams.current.debugViewModel
    val uiState by debugViewModel.uiState.collectAsState()
    Column(modifier = modifier) {
        Text(text = "Devices:", color = Color.Magenta)
        uiState.devices.forEach { dev ->
            Text(text = dev.modelName + ":", color = Color.Green)
            Text(text = dev.capabilities.toString())
            Text(text = "Services:", color = Color.Yellow)
            Text(text = dev.services.toString())
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
        }
    }

    Text(text = debugViewModel.discoveryManager?.connectableDeviceStore?.storedDevices.toString())
    Spacer(modifier = Modifier.padding(vertical = 16.dp))
    Text(text = debugViewModel.discoveryManager?.discoveryProviders.toString())
    Spacer(modifier = Modifier.padding(vertical = 8.dp))
    Text(text = debugViewModel.discoveryManager?.allDevices.toString())
}

private data class DebugParams(
    val debugViewModel: DebugViewModel
)