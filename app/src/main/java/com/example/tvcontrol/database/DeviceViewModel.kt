package com.example.tvcontrol.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.connectsdk.device.ConnectableDevice
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class DeviceViewModel(application: Application): AndroidViewModel(application) {
    private val deviceDao: DeviceDao = DeviceDatabase.getDatabase(application).deviceDao()
    private val _allDevices = MutableLiveData<List<Device>>()
    val allDevices: LiveData<List<Device>> get() = _allDevices

    init {
        fetchDatabase()
    }

    fun insertDevice(device: ConnectableDevice) = viewModelScope.launch {
        val dev = Device(modelName = device.modelName)
        deviceDao.insert(dev)
        fetchDatabase()
    }

    fun updateDevice(device: Device) = viewModelScope.launch {
        deviceDao.update(device)
        fetchDatabase()
    }

    fun deleteDevice(device: Device) = viewModelScope.launch {
        deviceDao.delete(device)
        fetchDatabase()
    }

    fun getDeviceById(id: Int): LiveData<Device> {
        return liveData {
            emit(deviceDao.getDeviceById(id))
        }
    }

    suspend fun existsDevice(device: ConnectableDevice): Boolean {
        return deviceDao.existsDevice(device.modelName)
    }

    private fun fetchDatabase() = viewModelScope.launch {
        _allDevices.value = deviceDao.getAllDevices()
    }
}