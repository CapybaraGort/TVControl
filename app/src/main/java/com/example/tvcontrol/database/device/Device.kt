package com.example.tvcontrol.database.device

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "device_table")
data class Device(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val modelName: String
)
