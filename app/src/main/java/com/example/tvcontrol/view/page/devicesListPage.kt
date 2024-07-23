package com.example.tvcontrol.view.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.tvcontrol.R
import com.example.tvcontrol.ui.theme.MyStyle
import com.example.tvcontrol.view.components.DeviceButton
import com.example.tvcontrol.viewModels.DeviceViewModel
import com.example.tvcontrol.viewModels.TVControlViewModel
import com.google.gson.Gson
import kotlinx.coroutines.launch

@Composable
fun DevicesListPage(
    modifier: Modifier = Modifier,
    deviceViewModel: DeviceViewModel,
    tvControlViewModel: TVControlViewModel,
    navController: NavHostController,
){
    val devs by deviceViewModel.allDevices.observeAsState(emptyList())
    val scope = rememberCoroutineScope()

    if (devs.isEmpty()){
        Column(modifier = modifier
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Column (
                modifier = Modifier.padding(bottom = 80.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                val imagePainter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_no_devices))
                Image(
                    painter = imagePainter,
                    contentDescription = "Search Tv",
                    modifier = Modifier.size(246.dp, 249.dp)
                )
                Text(text = stringResource(id = R.string.devices_not_found), style = MyStyle.text_H1)
            }
        }
    }
    else{
        LazyVerticalGrid(
            modifier = modifier
                .padding(20.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(devs){device ->
                DeviceButton(title = device.name, onClick = {
                    scope.launch {
                        val conDev = tvControlViewModel.getConnectableDevice(device)
                        conDev?.let {
                            tvControlViewModel.connectToDevice(device = it, onConnect = {
                                navController.navigate("DeviceControl/${device.name}")
                            })
                        }
                    }
                },
                onChange = {
                    scope.launch {
                        val conDev = tvControlViewModel.getConnectableDevice(device)
                        tvControlViewModel.setCurrentDevice(conDev)
                        val serializedDevice = Gson().toJson(device)
                        navController.navigate("SaveDevice/${serializedDevice}")
                    }
                },
                onDelete = {
                    scope.launch {
                        deviceViewModel.deleteDevice(device)
                        tvControlViewModel.resetCurrentDevice()
                    }
                })
            }
        }
    }
}