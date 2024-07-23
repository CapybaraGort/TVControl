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
import com.example.tvcontrol.ui.theme.MyStyle
import com.example.tvcontrol.view.components.Recommendations

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun SearchTvPage() {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .windowInsetsPadding(WindowInsets.systemBars),
        topBar = {
            TopAppBar(title = {
                Spacer(modifier = Modifier)
            })
        })
    { innerPadding ->
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Box(modifier = Modifier
                .padding(
                    start = 69.dp,
                    end = 69.dp,
                )
                .padding(innerPadding)) {

                val imagePainter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.tv_search))
                Image(
                    painter = imagePainter,
                    contentDescription = "Search Tv",
                    modifier = Modifier.size(346.dp, 248.dp)
                )
            }
            Text( modifier = Modifier
                .fillMaxWidth(),
                text = stringResource(id = R.string.searhing) + "...",
                textAlign = TextAlign.Center,
                style = MyStyle.text_H1
            )

            Box(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                )) {
                Recommendations(modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 20.dp))
            }
        }
    }
}