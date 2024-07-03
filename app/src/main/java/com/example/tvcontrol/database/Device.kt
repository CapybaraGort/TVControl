package com.example.tvcontrol.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "device_table")
data class Device(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val deviceId: String,
    val modelName: String,
    val ipAddress: String,
)
