package com.example.androidcorepractice.WorkManagerFromChatGPT

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.androidcorepractice.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // - (16-10-2024)
        // The code you've provided is used to schedule a one-time
        // background task in Android using WorkManager, which is an
        // API designed to handle tasks that need to run outside the
        // main app thread (like sending data to a server or doing
        // long-running work).

        // Schedule the work

        // OneTimeWorkRequest.Builder(MyWorker::class.java)
        // OneTimeWorkRequest: This is a type of task that runs once.
        // It can be scheduled to run immediately or at a specific
        // time in the future.
        val workRequest = OneTimeWorkRequest.Builder(MyWorker::class.java).build()
        // .Builder(MyWorker::class.java): This is how you create the
        // request. You specify the task (or worker) that will run.
        // In this case, MyWorker is a class you created that defines
        // the task to be done. It extends a class called Worker, which
        // is where you write the actual code for the background task.

        WorkManager.getInstance(applicationContext).enqueue(workRequest)
        // WorkManager: This is the Android system's way of handling
        // background tasks. It manages when and how the task is executed.
        // getInstance(applicationContext): This gets the instance of
        // the WorkManager. You need the applicationContext because
        // WorkManager needs context about the application to execute
        // the task properly. applicationContext refers to the global
        // context of the app, not tied to any specific activity.

        // .enqueue(workRequest): This tells the WorkManager to actually
        // start the task you've built. By calling this, you're telling the
        // system to put your task in the queue, and it will execute based
        // on the system's scheduling (it may run immediately or be delayed
        // if necessary).
    }
}