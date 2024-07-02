package com.example.tvcontrol

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.connectsdk.device.ConnectableDevice
import com.connectsdk.discovery.DiscoveryManager
import com.connectsdk.discovery.DiscoveryManagerListener
import com.connectsdk.service.capability.VolumeControl
import com.connectsdk.service.capability.listeners.ResponseListener
import com.connectsdk.service.command.ServiceCommandError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

private const val DEBUG_TAG = "TVControlViewModelLog"

class TVControlViewModel(private val app: Application) : AndroidViewModel(app) {
    private val _uiState = MutableStateFlow(TVControlState())
    val uiState: StateFlow<TVControlState> = _uiState

    private val defaultResponseListener = object : ResponseListener<Any> {
        override fun onError(error: ServiceCommandError?) {
            Log.e(DEBUG_TAG, error?.message.toString())
        }

        override fun onSuccess(obj: Any?) {
            Log.d(DEBUG_TAG, obj.toString())
        }

    }

    private var discoveryManager: DiscoveryManager? = null
    private var currentDevice: ConnectableDevice? = null

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

    fun startDiscovery(){
        viewModelScope.launch {
            DiscoveryManager.init(app.applicationContext)
            discoveryManager = DiscoveryManager.getInstance()
            discoveryManager?.start()
            discoveryManager?.addListener(object : DiscoveryManagerListener {
                override fun onDeviceAdded(manager: DiscoveryManager?, device: ConnectableDevice?) {
                    device?.let {
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

    fun connectToDevice(device: ConnectableDevice) {
        viewModelScope.launch {
            currentDevice = device
            currentDevice?.connect()
        }
    }

    fun volumeUp() {
        currentDevice?.getCapability(VolumeControl::class.java)?.volumeUp(defaultResponseListener)
    }

    fun volumeDown() {
        currentDevice?.getCapability(VolumeControl::class.java)?.volumeDown(defaultResponseListener)
    }

    fun getCapabilities(): List<String> {
        return currentDevice?.capabilities ?: listOf()
    }

    override fun onCleared() {
        super.onCleared()
        discoveryManager?.stop()
    }
}