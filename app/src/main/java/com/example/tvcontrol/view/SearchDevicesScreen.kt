package com.example.tvcontrol.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.connectsdk.device.ConnectableDevice
import com.example.tvcontrol.view.page.ConnectionErrorPage
import com.example.tvcontrol.view.page.SearchTvPage
import com.example.tvcontrol.view.page.TVSelectList
import com.example.tvcontrol.viewModels.TVControlViewModel
import io.intercom.android.sdk.Intercom
import kotlinx.coroutines.launch


@Composable
fun SearchScreen(
    tvControlViewModel: TVControlViewModel,
    onDeviceConnected: () -> Unit
) {
    val uiState by tvControlViewModel.uiState.collectAsState()

    if (uiState.devices.isEmpty()) {
        SearchTvPage()
    } else if (uiState.devices.size > 0) {
        TVSelectList(
            devices = uiState.devices,
            onClickToDevice = tvControlViewModel::connectToDevice,
            onDeviceConnected = onDeviceConnected
        )
    } else if (uiState.error != null) {
        ConnectionErrorPage(tvControlViewModel::startDiscovery)
    }
}