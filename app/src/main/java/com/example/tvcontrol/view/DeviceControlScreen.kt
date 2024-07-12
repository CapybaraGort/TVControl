package com.example.tvcontrol.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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
import com.connectsdk.device.ConnectableDevice
import com.connectsdk.service.capability.KeyControl
import com.connectsdk.service.capability.PowerControl
import com.connectsdk.service.capability.VolumeControl
import com.connectsdk.service.capability.VolumeControl.MuteListener
import com.connectsdk.service.capability.listeners.ResponseListener
import com.connectsdk.service.command.ServiceCommandError
import com.example.tvcontrol.R
import kotlinx.coroutines.launch

private const val DEBUG_TAG = "DeviceControlLog"
private val defaultResponseListener = object : ResponseListener<Any> {
    override fun onError(error: ServiceCommandError?) {
        Log.e(DEBUG_TAG, error?.message.toString())
    }

    override fun onSuccess(obj: Any?) {
        Log.d(DEBUG_TAG, obj.toString())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceControlScreen(modifier: Modifier = Modifier, device: ConnectableDevice?, onNavigateBack: () -> Unit) {
    var currentScreen by rememberSaveable {
        mutableStateOf(Screen.Remote)
    }
    val scope = rememberCoroutineScope()

    Scaffold(modifier = modifier.windowInsetsPadding(WindowInsets.systemBars),
        topBar = { TopAppBar(title = {
            Row {
                IconButton(onClick = { scope.launch { onNavigateBack() } },
                    modifier = Modifier.background(MaterialTheme.colorScheme.tertiary, shape = CircleShape)
                ) {
                    Image(painter = painterResource(id = R.drawable.arrow_small_left_36), contentDescription = null)
                }
            }
        })}) { innerPadding ->
        when(currentScreen) {
            Screen.Remote -> RemoteControl(modifier = Modifier.padding(innerPadding), device = device)
        }
    }
}

@Composable
private fun RemoteControl(modifier: Modifier = Modifier, device: ConnectableDevice?) {
    Box(modifier = modifier
        .fillMaxSize()
        .padding(16.dp), contentAlignment = Alignment.Center) {

        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            ) {

            PowerButton(device = device)
            DirectionalPad(device = device)

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Volume(device = device)
                ChannelControl(device = device)
            }

            FunctionalButtons(device = device)
        }
    }
}

@Composable
private fun FunctionalButtons(modifier: Modifier = Modifier, device: ConnectableDevice?) {
    device?.getCapability(VolumeControl::class.java)?.getMute(object : MuteListener {
        override fun onError(error: ServiceCommandError?) {
        }

        override fun onSuccess(isMute: Boolean?) {
            Log.d(DEBUG_TAG, isMute.toString())
        }
    })

    var volumeImage by rememberSaveable {
        mutableStateOf(0)
    }

    Row (modifier = modifier
        .fillMaxWidth()
        .padding(top = 32.dp)
        .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        FunctionalButton(onClick = { device?.getCapability(KeyControl::class.java)?.back(defaultResponseListener) },
            painter = painterResource(id = R.drawable.arrow_small_left_36)
        )
        FunctionalButton(onClick = { device?.getCapability(KeyControl::class.java)?.home(defaultResponseListener) },
            painter = painterResource(id = R.drawable.house_chimney_24))

        FunctionalButton(onClick = {
            //device?.getCapability(VolumeControl::class.java)?.setMute(!isMute, defaultResponseListener)
            //isMute = !isMute
                                   },
            painter = painterResource(id = R.drawable.volume_36))
    }
}
@Composable
private fun FunctionalButton(modifier: Modifier = Modifier,
                             onClick: () -> Unit,
                             painter: Painter,
                             contentDescription: String? = null,
                             )
{
    val scope = rememberCoroutineScope()
    IconButton(modifier = modifier
        .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
        .size(56.dp),
        onClick = { scope.launch { onClick() } }
    ) {
        Image(painter = painter, contentDescription = contentDescription)
    }
}

@Composable
private fun DirectionalPad(modifier: Modifier = Modifier, device: ConnectableDevice?) {
    val scope = rememberCoroutineScope()

    Box(modifier = modifier
        .size(200.dp)
        .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)) {

        Button(
            onClick = {
                scope.launch {
                    device?.getCapability(KeyControl::class.java)?.ok(defaultResponseListener)
                }
            },
            colors = ButtonDefaults.buttonColors(contentColor = Color.Blue),
            shape = CircleShape,
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.Center)
        ) {
            Text("OK", color = Color.White)
        }

        IconButton(onClick = {
            scope.launch {
                device?.getCapability(KeyControl::class.java)?.up(defaultResponseListener)
            }
        },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(50.dp)
                .padding(4.dp)) {

            Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "")
        }

        IconButton(onClick = {
            scope.launch {
                device?.getCapability(KeyControl::class.java)?.down(defaultResponseListener)
            }
        },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(50.dp)
                .padding(4.dp)) {

            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "")
        }

        IconButton(onClick = {
            scope.launch {
                device?.getCapability(KeyControl::class.java)?.left(defaultResponseListener)
            }
        },
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(50.dp)
                .padding(4.dp)) {

            Icon(imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft, contentDescription = "")
        }

        IconButton(onClick = {
            scope.launch {
                device?.getCapability(KeyControl::class.java)?.right(defaultResponseListener)
            }
        },
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(50.dp)
                .padding(4.dp)) {

            Icon(imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight, contentDescription = null)
        }

    }
}

@Composable
private fun PowerButton(modifier: Modifier = Modifier, device: ConnectableDevice?) {
    var deviceEnabled by rememberSaveable {
        mutableStateOf(true)
    }
    val scope = rememberCoroutineScope()

    Row(modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween) {

        IconButton(onClick = {
            scope.launch {
                if(deviceEnabled) {
                    device?.getCapability(PowerControl::class.java)?.powerOff(defaultResponseListener)
                    deviceEnabled = false
                }
                else {
                    device?.getCapability(PowerControl::class.java)?.powerOn(defaultResponseListener)
                    deviceEnabled = true
                }
            } },
            colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Red),
            modifier = Modifier.size(56.dp))
        {
            Icon(imageVector = Icons.Default.Close, contentDescription = null)
        }
    }
}

@Composable
private fun ChannelControl(modifier: Modifier = Modifier, device: ConnectableDevice?) {
    Column(modifier = modifier.background(color = MaterialTheme.colorScheme.primary, shape = CircleShape),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        val scope = rememberCoroutineScope()

        IconButton(onClick = { scope.launch {
            device?.getCapability(com.connectsdk.service.capability.TVControl::class.java)
                ?.channelUp(defaultResponseListener)
        } })
        {
            Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null)
        }
        Text(text = "CH")
        IconButton(onClick = { scope.launch {
            device?.getCapability(com.connectsdk.service.capability.TVControl::class.java)
                ?.channelDown(defaultResponseListener)
        } })
        {
            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
        }
    }
}
@Composable
private fun Volume(device: ConnectableDevice?, modifier: Modifier = Modifier) {

    val scope = rememberCoroutineScope()

    Column(modifier = modifier.background(color = MaterialTheme.colorScheme.primary, shape = CircleShape),
        horizontalAlignment = Alignment.CenterHorizontally) {

        IconButton(onClick = {
            scope.launch {
                device?.getCapability(VolumeControl::class.java)?.volumeUp(defaultResponseListener)
            }
        }) {
            Image(painter = painterResource(id = R.drawable.plus_small_24), contentDescription = null)
        }
        Image(painter = painterResource(id = R.drawable.volume_24), contentDescription = null)
        IconButton(onClick = {
            scope.launch {
                device?.getCapability(VolumeControl::class.java)?.volumeDown(defaultResponseListener)
            }
        }) {
            Image(painter = painterResource(id = R.drawable.minus_small_24), contentDescription = null)
        }
    }
}

private enum class Screen {
    Remote
}

