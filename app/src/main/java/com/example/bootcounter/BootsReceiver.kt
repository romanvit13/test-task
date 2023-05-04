package com.example.bootcounter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.bootcounter.models.BootEvent

class BootsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("BootReceiver", "Received a reboot broadcast")

        val bootEventDao = TheApp.database?.bootEventDao()
        val bootEventTimestamp = System.currentTimeMillis()
        val bootEvent = BootEvent(0, bootEventTimestamp)

        bootEventDao?.insertAll(bootEvent)
    }
}