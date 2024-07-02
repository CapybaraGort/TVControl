package com.example.tvcontrol

import com.connectsdk.device.ConnectableDevice

data class TVControlState(
    val devices: List<ConnectableDevice> = emptyList(),
    val error: String? = null
)