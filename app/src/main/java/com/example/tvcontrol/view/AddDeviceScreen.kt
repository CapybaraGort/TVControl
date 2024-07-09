package com.example.tvcontrol.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.connectsdk.device.ConnectableDevice
import com.example.tvcontrol.R

@Composable
fun AddDeviceScreen(modifier: Modifier = Modifier,
                    device: ConnectableDevice,
                    addDevice: (String, ConnectableDevice) -> Unit,
                    onDeviceAdded: () -> Unit
) {
    var name by rememberSaveable {
        mutableStateOf(device.modelName)
    }

    Scaffold(modifier = modifier.windowInsetsPadding(WindowInsets.systemBars)) { innerPadding ->
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(verticalArrangement = Arrangement.spacedBy(32.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(value = name, onValueChange = {name = it})
                
                Button(onClick = {
                    if(name.isNotEmpty()) {
                        addDevice(name, device)
                        onDeviceAdded()
                    }
                }) {
                    Text(text = stringResource(id = R.string.add))
                }
            }
        }
    }
}
