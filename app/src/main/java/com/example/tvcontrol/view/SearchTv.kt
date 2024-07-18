package com.example.tvcontrol.view

import com.example.tvcontrol.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.tvcontrol.ui.theme.NavBack
import com.example.tvcontrol.ui.theme.Recommendations

@Composable
fun SearchTv() {
    MaterialTheme(
        typography = com.example.tvcontrol.ui.theme.AppTypography
    ) {
        Scaffold(modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)) { innerPadding ->
            Column {
                Box(modifier = Modifier
                    .padding(innerPadding)
                    .padding(
                        top = 48.dp,
                        start = 20.dp,
                        end = 20.dp
                    )) {
                    NavBack()
                }

                Box(modifier = Modifier
                    .padding(
                        top = 100.dp,
                        start = 69.dp,
                        end = 69.dp,
                        bottom = 10.dp
                    )) {

                    val imagePainter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.tv_search))
                    Image(
                        painter = imagePainter,
                        contentDescription = "Home Image",
                        modifier = Modifier.size(346.dp, 248.dp)
                    )
                }
                Text( modifier = Modifier.fillMaxWidth().padding(bottom = 136.dp),
                    text = "Поиск...",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )

                Box(modifier = Modifier
                    .padding(innerPadding)
                    .padding(
                        top = 58.dp,
                        start = 20.dp,
                        end = 20.dp,
                    )) {
                    Recommendations()
                }

            }

        }
    }
}