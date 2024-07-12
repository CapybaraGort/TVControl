package com.example.tvcontrol.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.connectsdk.device.ConnectableDevice
import com.connectsdk.device.ConnectableDeviceListener
import com.connectsdk.discovery.DiscoveryManager
import com.connectsdk.discovery.DiscoveryManagerListener
import com.connectsdk.service.CastService
import com.connectsdk.service.DeviceService
import com.connectsdk.service.capability.TVControl
import com.connectsdk.service.command.ServiceCommandError
import com.example.tvcontrol.TVControlState
import com.example.tvcontrol.database.device.Device
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

private const val DEBUG_TAG = "TVControlViewModelLog"

class TVControlViewModel(private val app: Application) : AndroidViewModel(app) {
    private val _uiState = MutableStateFlow(TVControlState())
    val uiState: StateFlow<TVControlState> = _uiState
    private var discoveryStarted = false
    private var discoveryManager: DiscoveryManager? = null
    private var _currentDevice: ConnectableDevice? = null
    val currentDevice
        get() = _currentDevice

    suspend fun getConnectableDevice(device: Device): ConnectableDevice? {
        val conDev = uiState.value.devices.find { it.modelName == device.modelName }
        return conDev
    }

    private fun checkDuplicates(device: ConnectableDevice): Boolean {
        return _uiState.value.devices.any { it.ipAddress == device.ipAddress }
    }

    private fun removeDuplicates(device: ConnectableDevice) {
        val duplicate = _uiState.value.devices.find { it.ipAddress == device.ipAddress }
        if((device.capabilities?.size ?: 0) > (duplicate?.capabilities?.size ?: 0))  {
            duplicate?.let {
                _uiState.value = _uiState.value.copy(
                    devices = (_uiState.value.devices - it)
                )
            }

            _uiState.value = _uiState.value.copy(
                devices = (_uiState.value.devices + device)
            )
        }
    }

    fun startDiscovery() {
        if(!discoveryStarted) {
            discoveryStarted = true
            viewModelScope.launch {
                DiscoveryManager.init(app.applicationContext)
                discoveryManager = DiscoveryManager.getInstance()
                discoveryManager?.start()
                discoveryManager?.pairingLevel = DiscoveryManager.PairingLevel.ON
                discoveryManager?.addListener(object : DiscoveryManagerListener {
                    override fun onDeviceAdded(manager: DiscoveryManager?, device: ConnectableDevice?) {
                        device?.let {
                            Log.d(DEBUG_TAG, "${it.modelName}: ${it.capabilities}")
                            viewModelScope.launch {
                                if (!checkDuplicates(it)) {
                                    _uiState.value = _uiState.value.copy(
                                        devices = (_uiState.value.devices + it)
                                    )
                                } else if (checkDuplicates(it)) {
                                    removeDuplicates(it)
                                } else {
                                    Log.e(DEBUG_TAG, "error")
                                }
                            }
                        }
                    }

                    override fun onDeviceUpdated(
                        manager: DiscoveryManager?,
                        device: ConnectableDevice?
                    ) {

                    }

                    override fun onDeviceRemoved(
                        manager: DiscoveryManager?,
                        device: ConnectableDevice?
                    ) {
                        device?.let {
                            viewModelScope.launch {
                                _uiState.value = _uiState.value.copy(
                                    devices = (_uiState.value.devices - it)
                                )
                            }
                        }
                    }

                    override fun onDiscoveryFailed(
                        manager: DiscoveryManager?,
                        error: ServiceCommandError?
                    ) {
                        viewModelScope.launch {
                            val errorMessage =
                                "Discovery failed: ${error?.message}, Code: ${error?.code}"
                            _uiState.value = _uiState.value.copy(error = errorMessage)
                            Log.e(DEBUG_TAG, errorMessage)
                        }
                    }
                })
            }
        }
    }

    fun resetCurrentDevice() {
        _currentDevice = null
    }

    fun connectToDevice(device: ConnectableDevice, onConnect : () -> Unit = {}) {
        viewModelScope.launch {
            _currentDevice = device 
            if(_currentDevice?.isConnected == true) {
                onConnect()
            } else {
                _currentDevice?.connect()
                _currentDevice?.addListener(object : ConnectableDeviceListener {
                    override fun onDeviceReady(device: ConnectableDevice?) {
                        onConnect()
                    }

                    override fun onDeviceDisconnected(device: ConnectableDevice?) {
                        TODO("Not yet implemented")
                    }

                    override fun onPairingRequired(
                        device: ConnectableDevice?,
                        service: DeviceService?,
                        pairingType: DeviceService.PairingType?
                    ) {
                        TODO("Not yet implemented")
                    }

                    override fun onCapabilityUpdated(
                        device: ConnectableDevice?,
                        added: MutableList<String>?,
                        removed: MutableList<String>?
                    ) {
                        TODO("Not yet implemented")
                    }

                    override fun onConnectionFailed(
                        device: ConnectableDevice?,
                        error: ServiceCommandError?
                    ) {
                        TODO("Not yet implemented")
                    }
                })
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        discoveryManager?.stop()
    }
}