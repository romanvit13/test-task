package com.example.bootcounter

import android.app.Application
import androidx.room.Room

class TheApp : Application() {
    companion object {
        var database: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "boot-counter-database"
        ).build()
    }

    override fun onTerminate() {
        super.onTerminate()
        database = null
    }
}