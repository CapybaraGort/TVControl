package com.example.tvcontrol.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.tvcontrol.R
import com.example.tvcontrol.ui.theme.MyStyle


@Composable
fun CustomButton(modifier: Modifier = Modifier, onClick: () -> Unit, text: String) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
            .padding(horizontal = 48.dp)
            .width(420.dp),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary)
    )
    {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MyStyle.text_H2,
            color = MaterialTheme.colorScheme.background
        )

    }
}

@Composable
fun NavAllIcons(modifier: Modifier = Modifier, onNavBack: () -> Unit, onSupport: () -> Unit, title: String) {
    Row (
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onNavBack,
            modifier = Modifier
                .width(40.dp)
                .height(40.dp),
            colors = IconButtonDefaults.iconButtonColors(),

            ) {
            val imagePainter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_arrow_left))
            Image(
                painter = imagePainter,
                contentDescription = "Back",
                modifier = Modifier.size(40.dp, 40.dp)
            )
        }
        TitleText(title = title, modifier = Modifier.width(200.dp))
        IconButton(
            onClick = onSupport,
            modifier = Modifier
                .width(40.dp)
                .height(40.dp),
            colors = IconButtonDefaults.iconButtonColors(),
        ) {
            val imagePainter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_support_nav))
            Image(
                painter = imagePainter,
                contentDescription = "Support",
                modifier = Modifier.size(40.dp, 40.dp)
            )
        }
    }
}

@Composable
fun NavSupportAndTitle(modifier: Modifier,onClick: () -> Unit, title: String) {
    Row (
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TitleText(title = title)
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .width(40.dp)
                .height(40.dp),
            colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onTertiaryContainer),
        ) {
            val imagePainter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_support_nav))
            Image(
                painter = imagePainter,
                contentDescription = "Support",
                modifier = Modifier.size(40.dp, 40.dp)
            )
        }
    }
}

@Composable
fun NavBackAndTitle(modifier: Modifier = Modifier, onClick: () -> Unit, title: String) {
    Row (
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .width(40.dp)
                .height(40.dp),
            colors = IconButtonDefaults.iconButtonColors(),

            ) {
            val imagePainter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_arrow_left))
            Image(
                painter = imagePainter,
                contentDescription = "Back",
                modifier = Modifier.size(40.dp, 40.dp)
            )
        }
        TitleText(title = title, modifier = Modifier.fillMaxWidth().padding(end = 40.dp))
    }
}

@Composable
fun NavTitleOnly(modifier: Modifier = Modifier, title: String) {
    Row (
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = MyStyle.text_H1
        )
    }
}

@Composable
fun NumberedListItem(index: Int, text: String) {
    Row(modifier = Modifier.padding(bottom = 8.dp)) {
        Text(
            text = "${index + 1}. ",
            style = MyStyle.text_P,
            color = MaterialTheme.colorScheme.scrim
        )
        Text(
            text = text,
            style = MyStyle.text_P,
            color = MaterialTheme.colorScheme.scrim
        )
    }
}

@Composable
fun Recommendations(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Column(
            modifier = Modifier.padding(vertical = 32.dp, horizontal = 32.dp)
        ) {
            Text(
                text = stringResource(id = R.string.make_sure) + ":",
                style = MyStyle.text_H3,
                color = MaterialTheme.colorScheme.scrim,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            NumberedListItem(0, stringResource(id = R.string.wifi_recommendation))
            NumberedListItem(1, stringResource(id = R.string.tv_on_recommendation))
            NumberedListItem(2, stringResource(id = R.string.vpn_recommendation))
        }
    }
}

@Composable
fun DeviceConnectedRecommendations(deviceName: String) {
    Box(modifier = Modifier.background(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(16.dp)
    )) {
        Column(
            modifier = Modifier.padding(vertical = 24.dp, horizontal = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.device_name_connected, deviceName),
                style = MyStyle.text_H3,
                color = MaterialTheme.colorScheme.scrim,
                textAlign = TextAlign.Center,
            )

            Text(
                text = stringResource(id = R.string.save_recommendation),
                style = MyStyle.text_P,
                color = MaterialTheme.colorScheme.scrim,
                textAlign = TextAlign.Center
            )
        }
    }
}

//@Composable
//fun BottomNavigation() {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(82.dp)
//            //.border()
//            .padding(top = 1.dp)
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//
//            Button(onClick = { /*TODO*/ }) {
//                val imagePainter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_nav_controller_active))
//                Image(painter = imagePainter, contentDescription = null, modifier = Modifier.size(40.dp, 40.dp))
//            }
//            BottomNavigationButton(R.drawable.ic_controller_default, R.drawable.ic_controller_active)
//            BottomNavigationButton(R.drawable.ic_burger_default, R.drawable.ic_burger_active)
//        }
//    }
//}

@Composable
fun BottomNavigationButton(icon: Int, onClick: () -> Unit) {
    IconButton(
        onClick = onClick
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
    }
}

@Composable
private fun TitleText(modifier: Modifier = Modifier, title: String) {
    Text(
        modifier = modifier,
        maxLines = 1,
        text = title,
        textAlign = TextAlign.Center,
        style = MyStyle.text_H1,
        overflow = TextOverflow.Ellipsis
    )
}
