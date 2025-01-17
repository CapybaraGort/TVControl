package com.example.tvcontrol.database.device

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface DeviceDao {
    @Insert
    suspend fun insert(device: Device)

    @Update
    suspend fun update(device: Device)

    @Delete
    suspend fun delete(device: Device)

    @Query("DELETE FROM device_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM device_table WHERE id = :id")
    suspend fun getDeviceById(id: Int): Device

    @Query("SELECT EXISTS(SELECT * FROM device_table WHERE modelName = :modelName)")
    suspend fun existsDevice(modelName: String): Boolean

    @Query("SELECT * FROM device_table")
    suspend fun getAllDevices() : List<Device>
}