package com.example.androidcorepractice.WorkManagerFullGuide

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.example.androidcorepractice.R
import kotlinx.coroutines.delay
import kotlin.random.Random

// - (05-10-2024)
// Now we will create a so called worker: worker is basically a class that
// defines our work manager task.
// This would potentially also be something very large, to do
// you always not use work manager, You can use it for very big file
// to download in the background reliably.

// We want to use workManager to get up with coroutines
class DownloadWorker(
    // This two will contain more specific information about the worker
    private val context: Context,
    private val workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    // ctrl+i
    // This is the function which will do the actual work
    // We can return Result which will come from workManagerAPi, and then
    // workManagerApi will use that result to determine, was the task successful
    // was the download successful, did something fail or should actually
    // retried later (that's also possible with work manager)

    // In this function we have to download the image using the API

    // - (06-10-2024)
    // WorkManager contains a behaviour of showing a easy notification
    // since in can. if we have a long running task it will
    // execute it in a foreground service, and when we want to show
    // notification then we need to create a notification channel
    // that is usually done in an application class
//    override suspend fun doWork(): Result {
//        startForegroundService()
//        delay(5000L)
//    }

    // - (07-10-2024)
    // This Kotlin function startForegroundService() is designed
    // to start a foreground service, which is a type of service
    // in Android that keeps running in the background but displays
    // a notification to let the user know it's active.
    private suspend fun startForegroundService() {
        // Since we are in the coroutineWorker
        // setForeground: This tells the system that the service
        // is now running in the foreground. It accepts an object
        // of type ForegroundInfo, which contains details about
        // the notification that will be shown.
        setForeground(
            // ForegroundInfo(...): This is an object that holds
            // information about the notification for the foreground service.
            ForegroundInfo(
                Random.nextInt(),   // Notification ID
                NotificationCompat.Builder(context, "download_channel")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentText("Downloading..")
                    .setContentTitle("Download in progress")
                    .build()
            )
        )
    }
}





