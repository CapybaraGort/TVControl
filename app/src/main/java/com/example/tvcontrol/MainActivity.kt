package com.example.tvcontrol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tvcontrol.debug.DebugViewModel
import com.example.tvcontrol.debug.DebugViewModelFactory
import com.example.tvcontrol.ui.theme.AppTheme
import com.example.tvcontrol.ui.theme.MyStyle
import com.example.tvcontrol.view.CustomNavHost
import com.example.tvcontrol.viewModels.DeviceViewModel
import com.example.tvcontrol.viewModels.TVControlViewModel
import com.example.tvcontrol.viewModels.factories.DeviceViewModelFactory
import com.example.tvcontrol.viewModels.factories.TVControlViewModelFactory
import io.intercom.android.sdk.Intercom

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val tvControlViewModel by viewModels<TVControlViewModel> { TVControlViewModelFactory(application) }
        val deviceViewModel by viewModels<DeviceViewModel> { DeviceViewModelFactory(application) }
        val debugViewModel by viewModels<DebugViewModel> { DebugViewModelFactory(application) }
        //debugViewModel.startDiscovery()
        deviceViewModel.deleteAllDevices()
        Intercom.initialize(this.application, "android_sdk-cccc4fb5f4b56e774c19372949072b63fe0972f4", "is7b8yel")
        Intercom.client().loginUnidentifiedUser()
        setContent {
            AppTheme {
                //DebugScreen(debugViewModel = debugViewModel)
                CustomNavHost(tvControlViewModel, deviceViewModel)


            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Test() {
    Button(shape = RoundedCornerShape(8.dp),
        onClick = { /*TODO*/ }) {
        Text(text = "dadad", style = MyStyle.testStyle)
    }
}