package com.example.tvcontrol.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.tvcontrol.R
import com.example.tvcontrol.destionations.Search
import com.example.tvcontrol.view.components.NavSupportAndTitle
import com.example.tvcontrol.view.page.DevicesListPage
import com.example.tvcontrol.viewModels.DeviceViewModel
import com.example.tvcontrol.viewModels.TVControlViewModel
import io.intercom.android.sdk.Intercom
import kotlinx.coroutines.launch

@Composable
fun MenuScreen(
    tvControlViewModel: TVControlViewModel,
    deviceViewModel: DeviceViewModel,
    navController: NavHostController
) {
    var visible by remember { mutableStateOf(false ) }
    LaunchedEffect(Unit) {
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally()
    ) {
        Scaffold(modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars),
            topBar = {
                DevicesTopBar()
            },
            floatingActionButton = {
                Button(
                    onClick = { navController.navigate(Search) },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.plus_small_24),
                        contentDescription = "add device"
                    )
                }
            }
        ) { innerPadding ->
            DevicesListPage(
                modifier = Modifier.padding(innerPadding),
                deviceViewModel = deviceViewModel,
                tvControlViewModel = tvControlViewModel,
                navController = navController
            )
        }
    }

}

@Composable
private fun DevicesTopBar(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    NavSupportAndTitle(
        modifier = modifier.padding(end = 20.dp, start = 20.dp),
        onClick = { scope.launch { Intercom.client().present() } },
        title = stringResource(id = R.string.devices)
    )
}