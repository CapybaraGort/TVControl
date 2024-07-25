package com.example.tvcontrol.view.page

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.connectsdk.device.ConnectableDevice
import com.example.tvcontrol.R
import com.example.tvcontrol.database.device.Device
import com.example.tvcontrol.ui.theme.MyStyle
import com.example.tvcontrol.view.components.CustomButton
import com.example.tvcontrol.view.components.DeviceConnectedRecommendations
import com.example.tvcontrol.view.components.NavBackAndTitle
import com.example.tvcontrol.viewModels.DeviceViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ControllerSetting(
    savedDevice: Device? = null,
    device: ConnectableDevice,
    onNavigateBack: () -> Unit,
    onDeviceSaved: () -> Unit,
    deviceViewModel: DeviceViewModel
) {
    val scope = rememberCoroutineScope()
    //var newDevice = savedDevice

    var deviceName by remember {
        mutableStateOf(
            savedDevice?.name ?: device.friendlyName
        )
    }
    var visible by remember { mutableStateOf(false ) }
    LaunchedEffect(Unit) {
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally()
    ) {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    NavBackAndTitle(
                        modifier = Modifier.padding(end = 20.dp, start = 8.dp),
                        onClick = { onNavigateBack() },
                        title = stringResource(id = R.string.save_device)
                    )
                })
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 20.dp)
                    .padding(top = 10.dp),
                verticalArrangement = Arrangement.spacedBy(64.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DeviceConnectedRecommendations(deviceName = device.modelName)
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = stringResource(id = R.string.device_name), style = MyStyle.text_H2)
                    Spacer(modifier = Modifier.height(20.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .border(
                                color = MaterialTheme.colorScheme.outlineVariant,
                                width = 1.dp,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            BasicTextField(
                                modifier = Modifier.weight(1f),
                                value = deviceName,
                                onValueChange = { deviceName = it }
                            )
                            Box(modifier = Modifier
                                .clip(CircleShape)
                                .clickable { deviceName = "" }
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_close),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
                Box(modifier = Modifier.fillMaxSize()) {
                    CustomButton(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 40.dp),
                        onClick = {
                            scope.launch {
                                if (deviceName.isNotEmpty()) {
                                    if(savedDevice == null) {
                                        deviceViewModel.insertDevice(deviceName, device)
                                    }
                                    else {
                                        savedDevice.name = deviceName
                                        deviceViewModel.updateDevice(savedDevice)
                                    }
                                    onDeviceSaved()
                                }
                            }
                        },
                        text = stringResource(id = R.string.save)
                    )
                }
            }
        }
    }
}

