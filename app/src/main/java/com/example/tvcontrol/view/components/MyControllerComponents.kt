package com.example.tvcontrol.view.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.connectsdk.core.TextInputStatusInfo
import com.connectsdk.service.capability.KeyControl
import com.connectsdk.service.capability.TextInputControl
import com.connectsdk.service.capability.TextInputControl.TextInputStatusListener
import com.connectsdk.service.command.ServiceCommandError
import com.example.tvcontrol.R
import com.example.tvcontrol.ui.theme.MyStyle
import com.example.tvcontrol.view.page.defaultResponseListener
import com.example.tvcontrol.view.page.localRemoteParams
import kotlinx.coroutines.launch

private val textInputStatusListener = object : TextInputStatusListener {
    override fun onError(error: ServiceCommandError?) {
        //Log.d(DEBUG_TAG, error?.message.toString())
    }

    override fun onSuccess(status: TextInputStatusInfo?) {
        // Log.d(DEBUG_TAG, status.toString())
    }
}

@Composable
fun PowerButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    StandardButton(modifier = modifier, onClick = onClick, painterId = R.drawable.ph_power)
}

@Composable
fun HomeButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    StandardButton(modifier = modifier, onClick = onClick, painterId = R.drawable.iconamoon_home_light)
}

@Composable
fun BackButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    StandardButton(
        modifier = modifier,
        onClick = onClick,
        painterId = R.drawable.humbleicons_arrow_go_back,
    )
}

@Composable
fun VolumeButton(
    modifier: Modifier = Modifier,
    onVolumeUp: () -> Unit,
    onVolumeDown: () -> Unit
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.tertiary, shape = CircleShape),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StandardButton(onClick = onVolumeUp, painterId = R.drawable.plus_small_24)
        Text(text = "VOL", style = MyStyle.text_H3, color = Color.White)
        StandardButton(onClick = onVolumeDown, painterId = R.drawable.minus_small_24)
    }
}

@Composable
fun KeyboardButton(modifier: Modifier = Modifier) {
    val device = localRemoteParams.current.device
    val scope = rememberCoroutineScope()
    val textInputControl = remember {
        device?.getCapability(TextInputControl::class.java)
    }
    textInputControl?.subscribeTextInputStatus(textInputStatusListener)
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.tertiary, shape = CircleShape)
                .sizeIn(minWidth = 100.dp, maxWidth = 150.dp),
            value = "",
            textStyle = MyStyle.hint_H2,
            shape = CircleShape,
            onValueChange = {
                scope.launch {
                    textInputControl?.sendText(it)
                }
            },
            placeholder = {
                Text(modifier = Modifier.fillMaxWidth(), text = stringResource(id = R.string.keyboard), style = MyStyle.hint_H2)
            })

        Button(
            modifier = Modifier.height(56.dp),
            onClick = {
                scope.launch {
                    textInputControl?.sendEnter()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary
            )) {
            Text(text = "Enter", style = MyStyle.text_H2)
        }
    }

}

@Composable
fun ChannelButton(
    modifier: Modifier = Modifier,
    onChannelUp: () -> Unit,
    onChannelDown: () -> Unit
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.tertiary, shape = CircleShape),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StandardButton(onClick = onChannelUp, painterId = R.drawable.arrow_up_24)
        Text(text = "CH", style = MyStyle.text_H3, color = Color.White)
        StandardButton(onClick = onChannelDown, painterId = R.drawable.arrow_down_24)
    }
}

@Composable
fun MuteButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    StandardButton(
        modifier = modifier.size(60.dp),
        onClick = onClick,
        painterId = R.drawable.mage_volume_mute
    )
}

@Composable
fun DirectionalPad(modifier: Modifier = Modifier) {
    val device = localRemoteParams.current.device
    val keyControl = remember {
        device?.getCapability(KeyControl::class.java)
    }
    val scope = rememberCoroutineScope()
    Box(
        modifier = modifier
            .size(200.dp)
            .background(color = MaterialTheme.colorScheme.tertiary, shape = CircleShape)
    ) {
        Button(
            modifier = Modifier
                .size(96.dp)
                .align(Alignment.Center),
            onClick = {
                scope.launch {
                    keyControl?.ok(defaultResponseListener)
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            )
        ) {
            Text(text = "ОК", style = MyStyle.text_H1)
        }
        DirectionalButton(
            modifier = Modifier.align(Alignment.TopCenter),
            onClick = {
                scope.launch {
                    keyControl?.up(defaultResponseListener)
                }
            },
            painter = painterResource(id = R.drawable.arrow_up_24)
        )
        DirectionalButton(
            modifier = Modifier.align(Alignment.CenterStart),
            onClick = {
                scope.launch {
                    keyControl?.left(defaultResponseListener)
                }
            },
            painter = painterResource(id = R.drawable.arrow_left_24)
        )
        DirectionalButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = {
                scope.launch {
                    keyControl?.down(defaultResponseListener)
                }
            },
            painter = painterResource(id = R.drawable.arrow_down_24)
        )
        DirectionalButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = {
                scope.launch {
                    keyControl?.right(defaultResponseListener)
                }
            },
            painter = painterResource(id = R.drawable.arrow_right_24)
        )
    }
}

@Composable
private fun DirectionalButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    painter: Painter
) {
    Button(
        modifier = modifier.size(48.dp),
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary
        )
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painter,
            contentDescription = null
        )
    }
}


@Composable
private fun StandardButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    painterId: Int,
    iconSize: Int = 28
) {
    Button(
        modifier = modifier
            .size(56.dp)
            .clip(CircleShape),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            painter = painterResource(id = painterId),
            contentDescription = null,
            modifier = Modifier.size(iconSize.dp),
        )
    }
}