package com.example.androidcorepractice.ForegroundServices

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.example.androidcorepractice.ui.theme.AndroidCorePracticeTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                0
            )
        }
        setContent {
            AndroidCorePracticeTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        // Here we are delivering action to the RunningService
                        Intent(applicationContext, RunningService::class.java).also {
                            // We are specifying the action
                            it.action = RunningService.Actions.START.toString()
                            startService(it)
                        }
                    }) {
                        Text("Start run")
                    }
                    Button(onClick = {
                        Intent(applicationContext, RunningService::class.java).also {
                            it.action = RunningService.Actions.STOP.toString()
                            // This only delivers intent to the service
                            startService(it)
                        }
                    }) {
                        Text("Stop run")
                    }
                }
            }
        }
    }
}