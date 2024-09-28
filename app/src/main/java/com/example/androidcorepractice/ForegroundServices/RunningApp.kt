package com.example.androidcorepractice.ForegroundServices

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

// - (27-09-2024)
// The class RunningApp extends Application. This means it will
// act as the base class for maintaining global application state
// and can be used for app-wide initialization tasks.

//The Application class in Android is the first thing that runs
// when your app starts, even before any activities or services.
class RunningApp: Application() {

    // The onCreate method is overridden from the Application class.
    // This method is called when the application is first created.
    // You typically use it to initialize resources, libraries, or
    // services needed across the app.
    override fun onCreate() {
        super.onCreate()

        // This conditional checks if the Android version on the
        // device is Oreo (API level 26) or higher because notification
        // channels are only required on Android 8.0 and above.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                // The unique ID of the notification channel.
                "running_channel",
                // The human-readable name for the channel, which will
                // be visible to the user in the app's settings.
                "Running Notifications",
                NotificationManager.IMPORTANCE_HIGH
            )
            // getSystemService(Context.NOTIFICATION_SERVICE): This is used
            // to get access to the system's NotificationManager service,
            // which is responsible for managing notifications.
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // The notificationManager.createNotificationChannel(channel)
            // creates and registers the notification channel with the system.
            // Once created, the app can send notifications using this channel.
            notificationManager.createNotificationChannel(channel)
        }
    }
}





