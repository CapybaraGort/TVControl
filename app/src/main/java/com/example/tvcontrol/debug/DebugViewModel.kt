package com.example.tvcontrol.debug

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.connectsdk.device.ConnectableDevice
import com.connectsdk.discovery.DiscoveryManager
import com.connectsdk.discovery.DiscoveryManagerListener
import com.connectsdk.service.command.ServiceCommandError
import com.example.tvcontrol.TVControlState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DebugViewModel(private val app: Application) : AndroidViewModel(app) {
    var discoveryManager: DiscoveryManager? = null
    private val _uiState = MutableStateFlow(TVControlState())
    val uiState: StateFlow<TVControlState> = _uiState

    fun startDiscovery() {
        viewModelScope.launch {
            DiscoveryManager.init(app.applicationContext)
            discoveryManager = DiscoveryManager.getInstance()
            discoveryManager?.start()
            discoveryManager?.addListener(object : DiscoveryManagerListener {
                override fun onDeviceAdded(manager: DiscoveryManager?, device: ConnectableDevice?) {
                    device?.let {
                        _uiState.value = _uiState.value.copy(
                            devices = (_uiState.value.devices + it)
                        )
                        Log.d("MyLog", "${it.modelName}: ${it.capabilities}")
                    }
                }
                override fun onDeviceUpdated(manager: DiscoveryManager?, device: ConnectableDevice?) {

                }
                override fun onDeviceRemoved(manager: DiscoveryManager?, device: ConnectableDevice?) {
                }
                override fun onDiscoveryFailed(manager: DiscoveryManager?, error: ServiceCommandError?) {
                }
            })
        }
    }
}