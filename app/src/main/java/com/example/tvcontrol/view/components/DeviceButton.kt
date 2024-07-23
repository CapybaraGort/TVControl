package com.example.tvcontrol.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tvcontrol.R
import com.example.tvcontrol.ui.theme.MyStyle


@Composable
fun DeviceButton(
    modifier: Modifier = Modifier,
    title: String = "",
    onClick: () -> Unit,
    onChange: () -> Unit,
    onDelete: () -> Unit
) {
    var showMenu by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .border(2.dp, Color.LightGray, shape = RoundedCornerShape(16.dp))
            .clickable { onClick() },
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Box(
                    modifier = Modifier
                        .offset(x = 14.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable {
                            showMenu = true
                        }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_menu_dots),
                        contentDescription = null,
                        tint = Color.Unspecified,
                    )
                    DropdownMenu(
                        modifier = Modifier.wrapContentSize(Alignment.TopEnd),
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            modifier = Modifier.clip(RoundedCornerShape(16.dp)),
                            text = { Text(text = stringResource(id = R.string.change)) },
                            onClick = { onChange() }
                        )
                        DropdownMenuItem(
                            modifier = Modifier.clip(RoundedCornerShape(16.dp)),
                            text = { Text(text = stringResource(id = R.string.delete)) },
                            onClick = { onDelete() }
                        )
                    }
                }
            }
            Image(
                modifier = Modifier.size(178.dp, 115.dp),
                painter = painterResource(id = R.drawable.ic_interior_tv),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title, style = MyStyle.text_P)
        }
    }
}