package com.example.tvcontrol.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.tvcontrol.ui.theme.MyStyle

@Composable
fun NavigationButton(onClick: () -> Unit = {}, image: Painter, title: String = "", description: String = ""){
    Box(modifier = Modifier
        .clip(RoundedCornerShape(16.dp))
        .clickable(onClick = onClick)
        .fillMaxWidth()
        .border(1.dp, MaterialTheme.colorScheme.outlineVariant, shape = RoundedCornerShape(16.dp))
        .background(color = MaterialTheme.colorScheme.surfaceVariant)
        .padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.clip(RoundedCornerShape(16.dp))
        ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp, 48.dp).clipToBounds()
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ){
                Text(text = title, style = MyStyle.text_H3, modifier = Modifier.padding(bottom = 8.dp))
                if (description != "") Text(text = description, style = MyStyle.text_P)
            }
        }
    }
}


