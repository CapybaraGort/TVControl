package com.example.tvcontrol.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.connectsdk.device.ConnectableDevice
import com.example.tvcontrol.R
import com.example.tvcontrol.TVControlViewModel

@Composable
fun SearchDevices(modifier: Modifier = Modifier, viewModel: TVControlViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    Scaffold(modifier = modifier, bottomBar = {
        BottomAppBar(modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars)) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Button(onClick = { viewModel.startDiscovery() }) {
                    Text(stringResource(R.string.search))
                }
            }
        }
    }) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding).windowInsetsPadding(WindowInsets.systemBars).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(uiState.devices) { device ->
                Device(device = device, connectToDevice = {viewModel.connectToDevice(device)})
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
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = device.modelName.toString(), style = MaterialTheme.typography.titleLarge)
            Text(text = device.ipAddress.toString(), style = MaterialTheme.typography.bodySmall)
        }
    }
}