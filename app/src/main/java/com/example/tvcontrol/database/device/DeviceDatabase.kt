package com.example.tvcontrol.database.device

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Device::class], version = 2)
abstract class DeviceDatabase: RoomDatabase() {
    abstract fun deviceDao(): DeviceDao

    companion object {
        @Volatile
        private var instance: DeviceDatabase? = null

        fun getDatabase(context: Context): DeviceDatabase {
            return instance ?: synchronized(this) {
                val inst = Room.databaseBuilder(
                    context.applicationContext,
                    DeviceDatabase::class.java,
                    "device_database.db").fallbackToDestructiveMigration().build()
                instance = inst
                inst
            }
        }
    }
}