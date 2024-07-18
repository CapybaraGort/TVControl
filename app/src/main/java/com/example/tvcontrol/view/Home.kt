package com.example.tvcontrol.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tvcontrol.R
import com.example.tvcontrol.ui.theme.CustomButton


@Composable
fun Home() {
    MaterialTheme(
        typography = com.example.tvcontrol.ui.theme.AppTypography
    ) {
        Scaffold(modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)) { innerPadding ->
            Column {
                Box(modifier = Modifier.padding(innerPadding).padding(
                    top = 110.dp,
                    start = 69.dp,
                    end = 69.dp
                )) {
                    val imagePainter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.home))
                    Image(
                        painter = imagePainter,
                        contentDescription = "Home Image",
                        modifier = Modifier.size(438.dp, 327.dp)
                    )
                }
                Text( modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 180.dp),
                    text = "Сделайте из своего смартфона пульт от телевизора!",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )
                CustomButton(modifier = Modifier, onClick = {},"Начать")
            }

        }
    }

}