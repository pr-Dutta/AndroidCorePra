package com.example.androidcorepractice.BroadcastsReceiver

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidcorepractice.R
import com.example.androidcorepractice.ui.theme.AndroidCorePracticeTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidCorePracticeTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // - (22-09-2024)
                    // It could be a reliable way to communicate with two app
                    // But this will only work if the app is running or if
                    // you specifically mention the particular app then
                    // it will work for not running app.
                    Button(onClick = {
                        sendBroadcast(
                            // Custom action for custom example
                            Intent("TEST_ACTION")
                        )
                    }) {
                        Text("Send Broadcast")
                    }
                }
            }
        }
    }
}