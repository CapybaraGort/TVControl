package com.example.tvcontrol.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.compose.rememberNavController
import com.connectsdk.device.ConnectableDevice
import com.example.tvcontrol.R
import com.example.tvcontrol.TVControlViewModel
import com.example.tvcontrol.database.Device
import com.example.tvcontrol.database.DeviceViewModel
import kotlinx.coroutines.launch

private val localParams = compositionLocalOf<ListOfDevicesParams> {
    error("onDeviceConnected not provided")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListOfDevicesScreen(
    tvControlViewModel: TVControlViewModel,
    deviceViewModel: DeviceViewModel,
    onDeviceConnected: () -> Unit,
) {
    val listOfDevicesParams = ListOfDevicesParams(
        onDeviceConnected = onDeviceConnected,
        allDevices = deviceViewModel.allDevices,
        getConnectableDevice = { device -> tvControlViewModel.getConnectableDevice(device) },
        connectToDevice = { device, onConnect -> tvControlViewModel.connectToDevice(device, onDeviceConnected) }
    )

    CompositionLocalProvider(localParams provides listOfDevicesParams) {
        Scaffold(modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars),
            topBar = { TopAppBar(title = {
                Row {
                    Text(modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.devices),
                        textAlign = TextAlign.Center)
                }
            })}
        ) { innerPadding ->
            Devices(modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
private fun Devices(modifier: Modifier = Modifier) {
    val devices by localParams.current.allDevices.observeAsState(emptyList())
    val scope = rememberCoroutineScope()

    LazyColumn(modifier = modifier
        .fillMaxSize()
        .windowInsetsPadding(WindowInsets.systemBars),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(devices) { device ->
            Device(device = device)
        }
    }
}

@Composable
private fun Device(modifier: Modifier = Modifier, device: Device) {
    val scope = rememberCoroutineScope()
    val params = localParams.current
    val onDeviceConnected = params.onDeviceConnected

    Button(onClick = {
        scope.launch {
            val conDev = params.getConnectableDevice(device)
            conDev?.let { params.connectToDevice(it, onDeviceConnected) }
        }
    }, shape = CircleShape, modifier = modifier) {
        Text(text = device.modelName, style = MaterialTheme.typography.titleLarge)
    }
}

private data class ListOfDevicesParams(
    val onDeviceConnected: () -> Unit,
    val allDevices: LiveData<List<Device>>,
    val getConnectableDevice: suspend (Device) -> ConnectableDevice?,
    val connectToDevice: suspend (ConnectableDevice, () -> Unit) -> Unit
)