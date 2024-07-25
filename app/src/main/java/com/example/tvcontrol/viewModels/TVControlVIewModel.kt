package com.example.tvcontrol.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.connectsdk.device.ConnectableDevice
import com.connectsdk.device.ConnectableDeviceListener
import com.connectsdk.discovery.DiscoveryManager
import com.connectsdk.discovery.DiscoveryManagerListener
import com.connectsdk.service.DeviceService
import com.connectsdk.service.DeviceService.PairingType
import com.connectsdk.service.command.ServiceCommandError
import com.example.tvcontrol.TVControlState
import com.example.tvcontrol.database.device.Device
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
class TVControlViewModel(private val app: Application) : AndroidViewModel(app) {
    private val _uiState = MutableStateFlow(TVControlState())
    val uiState: StateFlow<TVControlState> = _uiState
    private var discoveryManager: DiscoveryManager? = null
    private var _currentDevice: ConnectableDevice? = null
    private var discoveryStarted = false
    val currentDevice
        get() = _currentDevice

    suspend fun getConnectableDevice(device: Device): ConnectableDevice? {
        val conDev = uiState.value.devices.find { it.modelName == device.modelName }
        return conDev
    }

    fun setCurrentDevice(device: ConnectableDevice?)  {
        _currentDevice = device
    }

    fun startDiscovery() {
        if(!discoveryStarted) {
            viewModelScope.launch {
                discoveryStarted = true
                DiscoveryManager.init(app.applicationContext)
                discoveryManager = DiscoveryManager.getInstance()
                discoveryManager?.start()
                discoveryManager?.pairingLevel = DiscoveryManager.PairingLevel.ON
                discoveryManager?.addListener(object : DiscoveryManagerListener {
                    override fun onDeviceAdded(manager: DiscoveryManager?, device: ConnectableDevice?) {
                        device?.let {
                            Log.d(
                                DEBUG_TAG,
                                "${it.modelName}: ${it.capabilities.size} ${it.capabilities}"
                            )
                            if(it.capabilities.size > 4) {
                                viewModelScope.launch {
                                    _uiState.value = _uiState.value.copy(
                                        devices = (_uiState.value.devices + it)
                                    )
                                }
                            }
                        }
                        if (_uiState.value.error != null)
                            _uiState.value = _uiState.value.copy(error = null)
                    }

                    override fun onDeviceUpdated(
                        manager: DiscoveryManager?,
                        device: ConnectableDevice?
                    ) {
                        _uiState.value = _uiState.value.copy(error = null)
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

    fun sendPairingCode(code: String) {
        viewModelScope.launch {
            _currentDevice?.sendPairingKey(code)
        }
    }

    fun connectToDevice(device: ConnectableDevice, onConnect: () -> Unit = {}, onPairing:() -> Unit = {}) {
        viewModelScope.launch {
            _currentDevice = device
            _currentDevice?.setPairingType(PairingType.FIRST_SCREEN)
            if (_currentDevice?.isConnected == true) {
                onConnect()
            } else {
                _currentDevice?.connect()
                _currentDevice?.addListener(object : ConnectableDeviceListener {
                    override fun onDeviceReady(device: ConnectableDevice?) {
                        onConnect()
                    }

                    override fun onDeviceDisconnected(device: ConnectableDevice?) {

                    }

                    override fun onPairingRequired(
                        device: ConnectableDevice?,
                        service: DeviceService?,
                        pairingType: PairingType?
                    ) {
                        onPairing()
                        device?.onPairingSuccess(service)
                    }

                    override fun onCapabilityUpdated(
                        device: ConnectableDevice?,
                        added: MutableList<String>?,
                        removed: MutableList<String>?
                    ) {

                    }

                    override fun onConnectionFailed(
                        device: ConnectableDevice?,
                        error: ServiceCommandError?
                    ) {
                        Log.e(DEBUG_TAG, "${device?.modelName}: ${error?.message}")
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

private const val DEBUG_TAG = "TVControlViewModelLog"

