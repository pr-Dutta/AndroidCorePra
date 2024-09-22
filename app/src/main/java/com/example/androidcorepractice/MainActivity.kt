package com.example.androidcorepractice

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.androidcorepractice.ui.theme.AndroidCorePracticeTheme
import com.example.androidcorepractice.BroadcastsReceiver.AirPlaneModeReceiver

class MainActivity : ComponentActivity() {

    // - (21-09-2024)
    // For Broadcast Receiver
    private val airPlaneModeReceiver = AirPlaneModeReceiver()

    // This is the viewModel instance
    private val viewModel by viewModels<ImageViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // - (21-09-2024)
        // We are registering Broadcast Receiver inside of our app
        // Dynamic Broadcast receiver, because we are dynamically
        // register and unregister the receiver according to our need

        //Dynamic receiver will only work if the app is active (running)
        registerReceiver(
            airPlaneModeReceiver,
            // What kind of intents our Receiver should be able to receive
            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        )

        // - (21-09-2024)
        // Static Broadcast Receiver will be triggered even if our app
        // is not launched

        // For static receiver you have to register it in the Manifest file

        setContent {
            AndroidCorePracticeTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // viewModel.uri?.let {} checks that is there
                    // uri exist of not
                    viewModel.uri?.let {
                        //AsyncImage comes from coil library
                        AsyncImage(
                            model = viewModel.uri,
                            contentDescription = null
                        )
                    }
                    Button(onClick = {
                        // Explicit intent - (18-09-2024)
//                        Intent(applicationContext, SecondActivity::class.java).also {
//                            startActivity(it)
//                        }

                        // Explicit Intent - (19-09-2024)
                        // This will open the youtube app main activity
                        // It's recommended to check for installation
                        // of the particular app

                        // Action_Main refers to launching of main activity
//                        Intent(Intent.ACTION_MAIN).also {
//                            it.`package` = "com.google.android.youtube"
//                            try {
//                                startActivity(it)
//                            } catch (e: ActivityNotFoundException) {
//                                e.printStackTrace()
//                            }
//                        }

                        // Implicit Intent
                        // This intent will open up a chooser to choose an app to send email
                        val intent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_EMAIL, arrayOf("test@gmail.com"))
                            putExtra(Intent.EXTRA_SUBJECT, "This is my subject")
                            putExtra(Intent.EXTRA_TEXT, "This is my content of the email.")
                        }
                        // This will check if there is app that can satisfy sending email action
                        if (intent.resolveActivity(packageManager) != null) {
                            startActivity(intent)
                        }

                    }) {
                        Text("Click Me")
                    }
                }
            }
        }
    }

    // This onNewIntent overload function will get the image uri to
    // open the image in our app
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Parcelables are just a way to package or serialize,
            // some data that is not a primitive data type.
            intent.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
        } else {
            intent.getParcelableExtra(Intent.EXTRA_STREAM)
        }
        viewModel.updateUri(uri)
    }

    // - (21-09-2024)
    // Destroying the Broadcast receiver
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(airPlaneModeReceiver)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidCorePracticeTheme {

    }
}