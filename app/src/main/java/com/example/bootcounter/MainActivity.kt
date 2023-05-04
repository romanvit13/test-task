package com.example.bootcounter

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.bootcounter.databinding.ActivityMainBinding
import com.example.bootcounter.models.BootEvent
import com.example.bootcounter.workers.NotificationScheduleWorker
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val events = TheApp.database?.bootEventDao()?.getAll()
        events?.let {
            Log.d("MainActivity", "events were retrieved..")
            if (it.isEmpty()) {
                return
            }

            val eventsSize = it.size
            val lastBootEvent = it[eventsSize - 1]
            val lastBootEventTimestamp = lastBootEvent.bootTimestamp

            binding.tvBootEventsCounter.text =
                "The boot was detected with the timestamp = ${lastBootEventTimestamp}"
        }

        this.applicationContext?.let { context ->
            val oneTimeWorkRequest = PeriodicWorkRequest.Builder(
                NotificationScheduleWorker::class.java,
                15,
                TimeUnit.MINUTES
            ).build()
            WorkManager.getInstance(context).enqueue(oneTimeWorkRequest)
        }
    }
}