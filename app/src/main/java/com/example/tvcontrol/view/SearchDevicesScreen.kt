package com.example.tvcontrol.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.tvcontrol.view.page.ConnectionErrorPage
import com.example.tvcontrol.view.page.SearchTvPage
import com.example.tvcontrol.view.page.TVSelectList
import com.example.tvcontrol.viewModels.TVControlViewModel


@Composable
fun SearchScreen(
    tvControlViewModel: TVControlViewModel,
    onDeviceConnected: () -> Unit
) {
    val uiState by tvControlViewModel.uiState.collectAsState()
    var visible by remember { mutableStateOf(false ) }

    if (uiState.devices.isEmpty()) {
        LaunchedEffect(Unit) {
            visible = true
        }
        AnimatedVisibility(
            visible = visible,
            enter = slideInHorizontally()
        ) {
            SearchTvPage()
        }
    } else if (uiState.devices.size > 0) {
        LaunchedEffect(Unit) {
            visible = true
        }
        AnimatedVisibility(
            visible = visible,
            enter = slideInHorizontally()
        ) {
            TVSelectList(
                devices = uiState.devices,
                onDeviceConnected = onDeviceConnected,
                tvControlViewModel = tvControlViewModel
            )
        }
    } else if (uiState.error != null) {
        LaunchedEffect(Unit) {
            visible = true
        }
        AnimatedVisibility(
            visible = visible,
            enter = slideInHorizontally()
        ) {
            ConnectionErrorPage(tvControlViewModel::startDiscovery)
        }
    }
}