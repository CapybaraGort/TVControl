package com.example.tvcontrol.view.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.connectsdk.device.ConnectableDevice
import com.example.tvcontrol.view.components.BackButton
import com.example.tvcontrol.view.components.ChannelButton
import com.example.tvcontrol.view.components.DirectionalPad
import com.example.tvcontrol.view.components.HomeButton
import com.example.tvcontrol.view.components.MuteButton
import com.example.tvcontrol.view.components.NavAllIcons
import com.example.tvcontrol.view.components.PowerButton
import com.example.tvcontrol.view.components.VolumeButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemoteControlPage(deviceName: String, device: ConnectableDevice, onNavigateBack: () -> Unit) {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .windowInsetsPadding(WindowInsets.systemBars),
        topBar = {
            TopAppBar(title = {
                NavAllIcons(
                    onClick = { onNavigateBack() },
                    title = deviceName,
                    modifier = Modifier.padding(end = 20.dp)
                )
            })
        },
        bottomBar = {
            BottomAppBar(modifier = Modifier.height(60.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(onClick = {}) {}
                    Button(onClick = {}) {}
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(start = 20.dp, end = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    PowerButton(onClick = {})
                }
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    HomeButton(onClick = {})
                    BackButton {}
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    DirectionalPad()
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    VolumeButton(onVolumeUp = {}, onVolumeDown = {})
                    MuteButton {

                    }
                    ChannelButton(onChannelUp = {}, onChannelDown = {})
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                }
            }
        }
    }
}