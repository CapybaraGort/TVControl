package com.example.tvcontrol.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.connectsdk.device.ConnectableDevice
import com.example.tvcontrol.R
import com.example.tvcontrol.TVControlViewModel

@Composable
fun ListOfFoundDevices(modifier: Modifier = Modifier,
                       viewModel: TVControlViewModel,
                       onDeviceConnected: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(modifier = modifier, bottomBar = {
        BottomAppBar(modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars)) {
            Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween) {
            }
        }
    }) { innerPadding ->
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