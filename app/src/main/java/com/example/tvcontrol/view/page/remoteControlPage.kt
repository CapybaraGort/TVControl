package com.example.tvcontrol.view.page

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.connectsdk.core.ProgramList
import com.connectsdk.device.ConnectableDevice
import com.connectsdk.service.capability.KeyControl
import com.connectsdk.service.capability.Launcher
import com.connectsdk.service.capability.Launcher.AppLaunchListener
import com.connectsdk.service.capability.PowerControl
import com.connectsdk.service.capability.TVControl
import com.connectsdk.service.capability.TVControl.ProgramListListener
import com.connectsdk.service.capability.VolumeControl
import com.connectsdk.service.capability.listeners.ResponseListener
import com.connectsdk.service.command.ServiceCommandError
import com.connectsdk.service.sessions.LaunchSession
import com.example.tvcontrol.R
import com.example.tvcontrol.ui.theme.MyStyle
import com.example.tvcontrol.view.components.BackButton
import com.example.tvcontrol.view.components.ChannelButton
import com.example.tvcontrol.view.components.DirectionalPad
import com.example.tvcontrol.view.components.HomeButton
import com.example.tvcontrol.view.components.KeyboardButton
import com.example.tvcontrol.view.components.MuteButton
import com.example.tvcontrol.view.components.NavAllIcons
import com.example.tvcontrol.view.components.PowerButton
import com.example.tvcontrol.view.components.VolumeButton
import io.intercom.android.sdk.Intercom
import kotlinx.coroutines.launch

val localRemoteParams = compositionLocalOf {
    RemoteControlParams(
        device = null,

        )
}

data class RemoteControlParams(
    val device: ConnectableDevice? = null,
)

private const val DEBUG_TAG = "RemoteLog"

val defaultResponseListener = object : ResponseListener<Any> {
    override fun onError(error: ServiceCommandError?) {
        Log.e(DEBUG_TAG, error?.message.toString())
    }

    override fun onSuccess(obj: Any?) {
        Log.d(DEBUG_TAG, obj.toString())
    }
}

val appLaunchListener = object : AppLaunchListener {
    override fun onError(error: ServiceCommandError?) {

    }

    override fun onSuccess(`object`: LaunchSession?) {

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemoteControlPage(deviceName: String, device: ConnectableDevice, onNavigateBack: () -> Unit) {
    val data by remember {
        mutableStateOf(
            RemoteControlParams(
                device = device
            )
        )
    }
    var visible by remember { mutableStateOf(false ) }
    LaunchedEffect(Unit) {
        visible = true
    }

    val scope = rememberCoroutineScope()
    CompositionLocalProvider(localRemoteParams provides data) {
        AnimatedVisibility(
            visible = visible,
            enter = slideInHorizontally()
        ) {
            Scaffold(modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.systemBars),
                topBar = {
                    TopAppBar(title = {
                        NavAllIcons(
                            onNavBack = { scope.launch { onNavigateBack() }  },
                            title = deviceName,
                            modifier = Modifier.padding(end = 20.dp),
                            onSupport = { scope.launch {
                                Intercom.client().present()
                            } }
                        )
                    })
                },
                bottomBar = {
                    BottomAppBar(
                        modifier = Modifier.height(60.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                onClick = { scope.launch { Nav.current.value = RemoteNav.Remote } },
                                modifier = Modifier.fillMaxWidth(0.5f),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Transparent,
                                    contentColor = Color.Black
                                )
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.solar_remote_controller_linear),
                                    contentDescription = "remote",
                                    tint = MaterialTheme.colorScheme.tertiary
                                )
                            }
                            Button(
                                onClick = { scope.launch { Nav.current.value = RemoteNav.Apps } },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Transparent,
                                    contentColor = Color.Black
                                )
                            ) {
                                Icon(
                                    modifier = Modifier.size(30.dp),
                                    painter = painterResource(id = R.drawable.apps_36),
                                    contentDescription = "apps",
                                    tint = MaterialTheme.colorScheme.tertiary
                                )
                            }
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
                    when (Nav.current.value) {
                        RemoteNav.Remote -> Remote()
                        RemoteNav.Apps -> Apps()
                    }
                }
            }
        }

    }
}

@Composable
private fun Remote(modifier: Modifier = Modifier) {
    val data = localRemoteParams.current

    val device by remember {
        mutableStateOf(data.device)
    }
    val keyControl = remember {
        device?.getCapability(KeyControl::class.java)
    }
    val volumeControl = remember {
        device?.getCapability(VolumeControl::class.java)
    }
    val channelControl = remember {
        device?.getCapability(TVControl::class.java)
    }

    val scope = rememberCoroutineScope()

    var isMute by rememberSaveable {
        mutableStateOf(false)
    }
    var isPowerOff by rememberSaveable {
        mutableStateOf(false)
    }

    channelControl?.subscribeProgramList(object : ProgramListListener {
        override fun onError(error: ServiceCommandError?) {
        }

        override fun onSuccess(`object`: ProgramList?) {
        }

    })

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            PowerButton {
                scope.launch {
                    if(!isPowerOff) {
                        device?.getCapability(PowerControl::class.java)?.powerOff(
                            defaultResponseListener
                        )
                        isPowerOff = true
                    } else {
                        device?.getCapability(PowerControl::class.java)?.powerOn(
                            defaultResponseListener
                        )
                        isPowerOff = false
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HomeButton {
                scope.launch {
                    keyControl?.home(defaultResponseListener)
                }
            }
            BackButton {
                scope.launch {
                    keyControl?.back(defaultResponseListener)
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            DirectionalPad()
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            VolumeButton(
                onVolumeUp = {
                    scope.launch {
                        volumeControl?.volumeUp(defaultResponseListener)
                    }
                },
                onVolumeDown = {
                    scope.launch {
                        volumeControl?.volumeDown(defaultResponseListener)
                    }
                })
            MuteButton {
                scope.launch {
                    volumeControl?.setMute(!isMute, defaultResponseListener)
                    isMute = !isMute
                }
            }
            ChannelButton(onChannelUp = {
                scope.launch {
                    channelControl?.channelUp(defaultResponseListener)
                }
            }, onChannelDown = {
                scope.launch {
                    channelControl?.channelDown(defaultResponseListener)
                }
            })
        }
        KeyboardButton()
    }
}

@Composable
private fun Apps(modifier: Modifier = Modifier) {
    val device = localRemoteParams.current.device
    val scope = rememberCoroutineScope()
    val launcher = remember {
        device?.getCapability(Launcher::class.java)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AppButton(
                painter = painterResource(id = R.drawable.youtube_36),
                text = "YouTube",
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                )
            ) {
                scope.launch {
                    launcher?.launchYouTube("", appLaunchListener)
                }
            }
            AppButton(
                painter = painterResource(id = R.drawable.netflix_36),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = MaterialTheme.colorScheme.onTertiary
                )
            ) {
                scope.launch {
                    launcher?.launchNetflix("", appLaunchListener)
                }
            }
        }
    }
}

@Composable
private fun AppButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    text: String = "",
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .width(150.dp)
            .height(60.dp),
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        colors = colors
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                painter = painter,
                contentDescription = text,
                modifier = if (text.isEmpty()) Modifier.fillMaxSize() else Modifier
            )
            if (text.isNotEmpty()) {
                Text(text = text, style = MyStyle.text_H3)
            }
        }
    }
}

private object Nav {
    var current = mutableStateOf(RemoteNav.Remote)
}

private enum class RemoteNav {
    Remote, Apps
}