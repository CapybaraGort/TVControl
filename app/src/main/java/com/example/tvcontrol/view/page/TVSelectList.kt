package com.example.tvcontrol.view.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.connectsdk.device.ConnectableDevice
import com.example.tvcontrol.R
import com.example.tvcontrol.view.components.NavSupportAndTitle
import com.example.tvcontrol.view.components.NavigationButton
import io.intercom.android.sdk.Intercom
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TVSelectList(
    modifier: Modifier = Modifier,
    devices: List<ConnectableDevice>,
    onClickToDevice: (ConnectableDevice, () -> Unit) -> Unit,
    onDeviceConnected: () -> Unit
) {
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                NavSupportAndTitle(
                    modifier = Modifier.padding(end = 20.dp, start = 8.dp),
                    onClick = { scope.launch { Intercom.client().present() } },
                    title = stringResource(id = R.string.select_a_device)
                )
            })
        },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 36.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(devices) { device ->
                    NavigationButton(
                        onClick = { onClickToDevice(device, onDeviceConnected) },
                        image = painterResource(id = R.drawable.ic_tv_list),
                        title = device.friendlyName
                    )
                }
            }
        }
    }
}

