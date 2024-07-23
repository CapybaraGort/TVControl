package com.example.tvcontrol.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tvcontrol.R
import com.example.tvcontrol.ui.theme.MyStyle

@Composable
fun PowerButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    StandardButton(modifier = modifier, onClick = onClick, painterId = R.drawable.power_36)
}

@Composable
fun HomeButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    StandardButton(modifier = modifier, onClick = onClick, painterId = R.drawable.house_blank_36)
}

@Composable
fun BackButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    StandardButton(
        modifier = modifier,
        onClick = onClick,
        painterId = R.drawable.arrow_small_left_36,
        iconSize = 44)
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
    StandardButton(modifier = modifier.size(60.dp), onClick = onClick, painterId = R.drawable.volume_mute_36)
}

@Composable
fun DirectionalPad(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(200.dp)
            .background(color = MaterialTheme.colorScheme.tertiary, shape = CircleShape)
    ) {
        Button(
            modifier = Modifier
                .size(96.dp)
                .align(Alignment.Center),
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            )
        ) {
            Text(text = "ОК", style = MyStyle.text_H1)
        }
        DirectionalButton(
            modifier = Modifier.align(Alignment.TopCenter),
            onClick = { /*TODO*/ },
            image = Icons.Default.KeyboardArrowUp)
        DirectionalButton(
            modifier = Modifier.align(Alignment.CenterStart),
            onClick = { /*TODO*/ },
            image = Icons.AutoMirrored.Default.KeyboardArrowLeft)
        DirectionalButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = { /*TODO*/ },
            image = Icons.Default.KeyboardArrowDown)
        DirectionalButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = { /*TODO*/ },
            image = Icons.AutoMirrored.Default.KeyboardArrowRight)
    }
}
@Composable
private fun DirectionalButton(modifier: Modifier = Modifier, onClick: () -> Unit, image: ImageVector) {
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
            imageVector = image,
            contentDescription = null
        )
    }
}


@Composable
private fun StandardButton(modifier: Modifier = Modifier, onClick: () -> Unit, painterId: Int, iconSize: Int = 28) {
    Button(
        modifier = modifier
            .size(56.dp)
            .clip(CircleShape),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary),
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            painter = painterResource(id = painterId),
            contentDescription = null,
            modifier = Modifier.size(iconSize.dp),
        )
    }
}