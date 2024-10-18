package com.example.androidcorepractice.WorkManagerFromChatGPT

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.androidcorepractice.R
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Schedule the work

        // - (17 -10-2024)
        // If you want to add constraints like "only run when connected
        // to Wi-Fi", you can add them in the same place where you're
        // scheduling the work.

        // Constraints.Builder()
        //This line creates an instance of the Constraints.Builder class,
        // which allows you to specify conditions or constraints that
        // must be met before a WorkManager task can be executed.

        // This method is called on the Constraints.Builder object and
        // specifies that the task (in this case, a Worker) should only
        // run when the device is connected to a network. NetworkType.CONNECTED
        // means that the work will run when the device is connected to
        // any type of network (either Wi-Fi or cellular data).

        // .setRequiresBatteryNotLow(true)
        // This sets another constraint: the work will only run when the
        // device's battery is not low. In other words, the task will be
        // postponed if the battery is running low. This is useful for
        // saving battery power on tasks that can be deferred.

        // .build()
        // This call finalizes the creation of the Constraints object
        // based on the specified conditions (network connected and
        // battery not low). After calling build(), the constraints
        // are ready to be used.
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

        // - (16-10-2024)
        // The code you've provided is used to schedule a one-time
        // background task in Android using WorkManager, which is an
        // API designed to handle tasks that need to run outside the
        // main app thread (like sending data to a server or doing
        // long-running work).

        // OneTimeWorkRequest.Builder(MyWorker::class.java)
        // OneTimeWorkRequest: This is a type of task that runs once.
        // It can be scheduled to run immediately or at a specific
        // time in the future.

        // - (17-10-2024)
        // .setConstraints(constraints)
        // This associates the Constraints (created earlier) with the
        // OneTimeWorkRequest. It ensures that the background task will
        // only be executed when the specified conditions (network connected
        // and battery not low) are met.

        // .build()
        // This finalizes the creation of the OneTimeWorkRequest.
        // After this, the request is ready to be passed to WorkManager
        // to schedule the background task.
//        val workRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
//            .setConstraints(constraints)
//            .build()
        // .Builder(MyWorker::class.java): This is how you create the
        // request. You specify the task (or worker) that will run.
        // In this case, MyWorker is a class you created that defines
        // the task to be done. It extends a class called Worker, which
        // is where you write the actual code for the background task.

        // - (17-10-2024)
        // PeriodicWorkRequest
        // This is a class from the Android WorkManager library.
        // PeriodicWorkRequest is used to define work that should
        // happen periodically (for example, every few hours, or daily).
        // Unlike OneTimeWorkRequest (which is for work that runs only
        // once), PeriodicWorkRequest is for recurring tasks.

        // Here, the Builder class is being called to configure and
        // create a PeriodicWorkRequest.

        // 1
        // This is the interval for how often the periodic work should be triggered.
        // In this case, the work will repeat every 1 unit of time
        // specified in the next argument (TimeUnit.HOURS).

        // TimeUnit.HOURS
        // TimeUnit is an enum in Java that represents time units (such
        // as seconds, minutes, hours, etc.).
        // TimeUnit.HOURS means the time unit is in hours, so the
        // periodic work will be triggered every 1 hour.
        val periodicWorkRequest = PeriodicWorkRequest.Builder(MyWorker::class.java, 1, TimeUnit.HOURS).build()

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