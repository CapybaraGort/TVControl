package com.example.tvcontrol.view.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tvcontrol.R
import com.example.tvcontrol.view.components.NavSupportAndTitle

@Preview(showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddingController(modifier: Modifier = Modifier){
    Scaffold(modifier = Modifier,
        topBar = { TopAppBar(title = { NavSupportAndTitle(modifier = Modifier.padding(end = 20.dp), onClick = {},title = "Пульт от телевизора") })}
    ){ innerPadding ->
        Column(modifier = modifier
            .padding(innerPadding)
            .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Column (
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally){
                val imagePainter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.tv_and_controller))
                Image(
                    painter = imagePainter,
                    contentDescription = "Search Tv",
                    modifier = Modifier.size(276.dp, 249.dp)
                )
                //BottomNavigationComponent()
            }
        }
    }
}