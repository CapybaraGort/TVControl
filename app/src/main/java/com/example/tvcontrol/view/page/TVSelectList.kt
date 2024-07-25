package com.example.tvcontrol.view.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.connectsdk.device.ConnectableDevice
import com.example.tvcontrol.R
import com.example.tvcontrol.ui.theme.MyStyle
import com.example.tvcontrol.view.components.NavSupportAndTitle
import com.example.tvcontrol.view.components.NavigationButton
import com.example.tvcontrol.viewModels.TVControlViewModel
import io.intercom.android.sdk.Intercom
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TVSelectList(
    modifier: Modifier = Modifier,
    devices: List<ConnectableDevice>,
    onDeviceConnected: () -> Unit,
    tvControlViewModel: TVControlViewModel
) {
    val scope = rememberCoroutineScope()
    var showDialog by remember {
        mutableStateOf(false)
    }
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
                        onClick = {
                            scope.launch {
                                tvControlViewModel.connectToDevice(device, onDeviceConnected, onPairing = {showDialog = true})
                            }
                        },
                        image = painterResource(id = R.drawable.ic_tv_list),
                        title = device.friendlyName,
                        description = device.capabilities.size.toString()
                    )
                }
            }

            if (showDialog) {
                Dialog(onDismissRequest = { showDialog = false }) {
                    Card {
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(top = 16.dp, bottom = 8.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = R.string.confirm_connection),
                                style = MyStyle.text_H3
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Button(onClick = {
                                showDialog = false
                            }) {
                                Text(
                                    text = stringResource(id = R.string.close),
                                    style = MyStyle.text_P
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

