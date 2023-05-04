package com.example.bootcounter.models

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class BootEvent(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "boot_timestamp") val bootTimestamp: Long,
)
