package com.example.bootcounter.dao

import androidx.room.Insert
import androidx.room.Query
import com.example.bootcounter.models.BootEvent

interface BootEventDao {
    @Query("SELECT * FROM bootEvent")
    fun getAll(): List<BootEvent>

    @Insert
    fun insertAll(vararg bootEvents: BootEvent)
}