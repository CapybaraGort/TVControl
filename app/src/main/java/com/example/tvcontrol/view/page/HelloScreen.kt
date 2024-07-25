package com.example.tvcontrol.view.page

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tvcontrol.R
import com.example.tvcontrol.view.components.CustomButton

@Preview(showSystemUi = true)
@Composable
fun HelloScreen(onClick: () -> Unit = {}) {
    var visible by remember { mutableStateOf(false ) }
    LaunchedEffect(Unit) {
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally()
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.systemBars)
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box {
                    val imagePainter =
                        rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.home))
                    Column {
                        Image(
                            painter = imagePainter,
                            contentDescription = "Home Image",
                            modifier = Modifier
                                .size(438.dp, 327.dp)
                                .padding(
                                    start = 69.dp,
                                    end = 69.dp
                                )
                        )
                        Text(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            text = stringResource(id = R.string.make_your_phone_a_remote),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleLarge,
                            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 20.dp, end = 20.dp)
                ) {
                    CustomButton(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 40.dp),
                        onClick = onClick,
                        text = stringResource(id = R.string.start)
                    )
                }
            }
        }
    }
}