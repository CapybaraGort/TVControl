package com.example.tvcontrol.view.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tvcontrol.R
import com.example.tvcontrol.ui.theme.AppTypography
import com.example.tvcontrol.view.components.CustomButton
import com.example.tvcontrol.view.components.NavBackAndTitle
import com.example.tvcontrol.view.components.Recommendations
import com.example.tvcontrol.ui.theme.MyStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectionErrorPage(researchDevices: () -> Unit) {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .windowInsetsPadding(WindowInsets.systemBars),
        topBar = { TopAppBar(title = { Spacer(modifier = Modifier) })})
    { innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)) {

            Box(modifier = Modifier
                .padding(
                    start = 69.dp,
                    end = 69.dp,
                    bottom = 10.dp
                )) {

                val imagePainter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.tv_error))
                Image(
                    painter = imagePainter,
                    contentDescription = "Home Image",
                    modifier = Modifier.size(346.dp, 248.dp)
                )
            }
            Text(modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.devices_not_found),
                textAlign = TextAlign.Center,
                style = MyStyle.text_H1
            )
            CustomButton(modifier = Modifier, onClick = { researchDevices() }, stringResource(id = R.string.refresh))
            Box(modifier = Modifier
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                )
                .fillMaxSize()) {
                Recommendations(modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 20.dp))
            }
        }

    }
}