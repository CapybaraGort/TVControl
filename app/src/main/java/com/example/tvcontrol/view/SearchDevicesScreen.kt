package com.example.tvcontrol.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.connectsdk.device.ConnectableDevice
import com.example.tvcontrol.viewModels.TVControlViewModel
import io.intercom.android.sdk.Intercom
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListOfFoundDevices(modifier: Modifier = Modifier,
                       viewModel: TVControlViewModel,
                       onDeviceConnected: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    Scaffold(modifier = modifier, topBar = {
        TopAppBar(title = {
            Button(onClick = { scope.launch { Intercom.client().present() } }
            ) {
    } })}) { innerPadding ->
        LazyColumn(modifier = Modifier
            .padding(innerPadding)
            .windowInsetsPadding(WindowInsets.systemBars)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {

            items(uiState.devices) { device ->
                Device(device = device, connectToDevice = {viewModel.connectToDevice(device, onConnect = {
                    onDeviceConnected()
                })})
            }
        }
    }
}

@Composable
private fun Device(modifier: Modifier = Modifier, device: ConnectableDevice,
                   connectToDevice: (ConnectableDevice) -> Unit) {
    Button(
        onClick = {
            connectToDevice(device)
        },
        modifier = modifier.width(330.dp),
        shape = CircleShape
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Text(text = device.friendlyName.toString(), style = MaterialTheme.typography.titleLarge)
        }
    }
}