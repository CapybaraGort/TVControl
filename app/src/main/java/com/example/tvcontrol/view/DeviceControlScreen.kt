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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.connectsdk.device.ConnectableDevice
import com.connectsdk.service.capability.KeyControl
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
        //Log.d(DEBUG_TAG, obj.toString())
    }
}

@Composable
fun DeviceControlScreen(modifier: Modifier = Modifier, device: ConnectableDevice?) {
    Scaffold(modifier = modifier.windowInsetsPadding(WindowInsets.systemBars)) { innerPadding ->
        ControlButtons(modifier = Modifier.padding(innerPadding), device = device)
    }
}

@Composable
private fun ControlButtons(modifier: Modifier = Modifier, device: ConnectableDevice?) {
    Box(modifier = modifier
        .fillMaxSize()
        .padding(16.dp), contentAlignment = Alignment.Center) {

        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            ) {

            PowerControl()
            DirectionalPad(device = device)

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                VolumeControl(device = device)
                ChannelControl()
            }
            
            FunctionalButtons(device = device)
        }
    }
}

@Composable
private fun FunctionalButtons(modifier: Modifier = Modifier, device: ConnectableDevice?) {
    Row (modifier = modifier
        .fillMaxWidth()
        .padding(top = 32.dp)
        .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        FunctionalButton(onClick = { /*back*/ }, painter = painterResource(id = R.drawable.arrow_small_left_36))
        FunctionalButton(onClick = { /*home*/ }, painter = painterResource(id = R.drawable.house_chimney_24))
    }
}
@Composable
private fun FunctionalButton(modifier: Modifier = Modifier,
                             onClick: () -> Unit,
                             painter: Painter,
                             contentDescription: String? = null,
                             ) {

    IconButton(modifier = modifier
        .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
        .size(56.dp),

        onClick = onClick
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

            Icon(imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight, contentDescription = "")
        }

    }
}

@Composable
private fun PowerControl(modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween) {

        IconButton(onClick = { },
            colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Red),
            modifier = Modifier.size(56.dp)) {
            Icon(imageVector = Icons.Default.Close, contentDescription = null)
        }

        IconButton(onClick = { },
            colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Red),
            modifier = Modifier.size(56.dp)) {
            Icon(imageVector = Icons.Default.Close, contentDescription = null)
        }
    }
}

@Composable
private fun ChannelControl(modifier: Modifier = Modifier) {
    Column(modifier = modifier.background(color = MaterialTheme.colorScheme.primary, shape = CircleShape),
        horizontalAlignment = Alignment.CenterHorizontally) {

        IconButton(onClick = { /*Channel up*/ }) {
            Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null)
        }
        Text(text = "CH")
        IconButton(onClick = { /*Channel up*/ }) {
            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
        }
    }
}
@Composable
private fun VolumeControl(device: ConnectableDevice?, modifier: Modifier = Modifier) {

    val scope = rememberCoroutineScope()

    Column(modifier = modifier.background(color = MaterialTheme.colorScheme.primary, shape = CircleShape),
        horizontalAlignment = Alignment.CenterHorizontally) {

        IconButton(onClick = {
            scope.launch {
                //device?.getCapability(VolumeControl::class.java)?.volumeUp(defaultResponseListener)
            }
        }) {
            Image(painter = painterResource(id = R.drawable.plus_small_24), contentDescription = null)
        }
        Image(painter = painterResource(id = R.drawable.volume_24), contentDescription = null)
        IconButton(onClick = {
            scope.launch {
                //device?.getCapability(VolumeControl::class.java)?.volumeDown(defaultResponseListener)
            }
        }) {
            Image(painter = painterResource(id = R.drawable.minus_small_24), contentDescription = null)
        }
    }
}

