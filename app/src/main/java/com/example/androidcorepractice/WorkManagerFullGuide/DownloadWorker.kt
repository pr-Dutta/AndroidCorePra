package com.example.androidcorepractice.WorkManagerFullGuide

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

// - ()05-10-2024
// Now we will create a so called worker: worker is basically a class that
// defines our work manager task.
// This would potentially also be something very large, to do
// you always not use work manager, You can use it for very big file
// to download in the background reliably.

// We want ot use workManager to get up with coroutines
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
    override suspend fun doWork(): Result {
        TODO("Not yet implemented")
    }
}