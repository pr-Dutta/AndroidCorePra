package com.example.androidcorepractice.WorkManagerFromChatGPT

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

// - (16-10-2024)
// This is a Kotlin class that defines a background worker for Android
// using WorkManager, a library used for scheduling and executing
// deferrable background tasks (like data syncing, file uploads, etc.).

// (context: Context, params: WorkerParameters): These are the
// parameters passed to the constructor of the MyWorker class.

// context: The context of the Android application. It provides
// access to application-specific resources and classes.

// params: An object that holds parameters for the worker, such as
// input data and information about how the work should be done.

// : Worker(context, params): This means MyWorker is a subclass of the
// Worker class, which is provided by the WorkManager library. The Worker
// class allows you to define background tasks that run in the background
// even if the app is closed.
class MyWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    // override fun doWork(): This is a function from the Worker
    // class that we are overriding. The doWork() function contains
    // the code that defines what the background task will do.

    // Whenever the system runs the background task, this function is called.
    override fun doWork(): Result {
        // Background task here (e.g., data sync, upload, etc.)
        println("WorkManager task is running")

        // Return success, failure, or retry
        return Result.success()
        // Result.success(): This tells WorkManager that the task was
        // completed successfully. There are other options too.
        //Result.failure(): If the task failed, you would return this to
        // indicate the task could not complete.
        //Result.retry(): If the task should try again (e.g., because
        // of a temporary issue like no internet), you return this.
    }
}

