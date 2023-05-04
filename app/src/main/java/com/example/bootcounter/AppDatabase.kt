package com.example.bootcounter

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bootcounter.dao.BootEventDao
import com.example.bootcounter.models.BootEvent

@Database(entities = [BootEvent::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bootEventDao(): BootEventDao
}