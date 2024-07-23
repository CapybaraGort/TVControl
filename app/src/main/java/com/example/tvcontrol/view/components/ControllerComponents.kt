/*
package com.example.tvcontrol.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tvcontrol.R

@Composable
fun PowerButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(64.dp),
        colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.tertiary, contentColor = MaterialTheme.colorScheme.onTertiary)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_controller_power),
            contentDescription = "Power",
            modifier = Modifier.size(64.dp),
        )
    }
}

@Composable
fun SourceButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(64.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_controller_source),
            contentDescription = "Source",
            modifier = Modifier.size(64.dp),
        )
    }
}

@Composable
fun PlayPauseButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(72.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_controller_play_pause),
            contentDescription = "Play and pause",
            modifier = Modifier.size(72.dp),
        )
    }
}

@Composable
fun HomeButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(72.dp)
            .offset(y = (-52).dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_controller_home),
            contentDescription = "Home",
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun BackButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(72.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_controller_back),
            contentDescription = "Back",
            modifier = Modifier.size(72.dp),
        )
    }
}

@Composable
fun SubmitButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(72.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_controller_submit),
            contentDescription = "Submit",
            modifier = Modifier.size(72.dp),
        )
    }
}

@Composable
fun UpButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(56.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_controller_arrow),
            contentDescription = "Navigation",
            modifier = Modifier.size(40.dp),
        )
    }
}

@Composable
fun RightButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(56.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_controller_arrow),
            contentDescription = "Navigation",
            modifier = Modifier
                .size(40.dp)
                .rotate(90f),
        )
    }
}

@Composable
fun DownButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(56.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_controller_arrow),
            contentDescription = "Navigation",
            modifier = Modifier
                .size(40.dp)
                .rotate(180f),
        )
    }
}

@Composable
fun LeftButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(56.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_controller_arrow),
            contentDescription = "Navigation",
            modifier = Modifier
                .size(40.dp)
                .rotate(-90f),
        )
    }
}

@Composable
fun UpVolumeButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(90.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_controller_plus),
            contentDescription = "Increase volume",
            modifier = Modifier.size(48.dp),
        )
    }
}

@Composable
fun DownVolumeButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(90.dp)

    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_controller_minus),
            contentDescription = "Turn down the volume",
            modifier = Modifier.size(48.dp),
        )
    }
}

@Composable
fun MuteButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(64.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_controller_mute),
            contentDescription = "Mute",
            modifier = Modifier.size(64.dp),
        )
    }
}

@Composable
fun NextChannelButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(90.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_controller_arrow),
            contentDescription = "Next channel",
            modifier = Modifier.size(48.dp),
        )
    }
}

@Composable
fun PreviousChannelButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(90.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_controller_arrow),
            contentDescription = "Previous volume",
            modifier = Modifier
                .size(48.dp)
                .rotate(180f)
        )
    }
}*/
