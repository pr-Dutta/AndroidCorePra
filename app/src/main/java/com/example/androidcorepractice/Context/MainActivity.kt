package com.example.androidcorepractice.Context

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidcorepractice.R
import com.example.androidcorepractice.ui.theme.AndroidCorePracticeTheme

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // - (13-09-2024)
        // We can refer application context from different activity.
        // Application context in not connected to your app UI.
        this.applicationContext

        viewModel.context = this

        // - (13-09-2024)
        // This is possible because the activity is a subclass of context class
        // and its same for Application.
        val myContext: Context = this@MainActivity

        // We can request permission here
        ActivityCompat.requestPermissions(
            this,
            arrayOf(),
            0
        )

        setContent {
            AndroidCorePracticeTheme {

            }
        }
    }
}