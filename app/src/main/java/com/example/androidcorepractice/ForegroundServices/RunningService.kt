package com.example.androidcorepractice.ForegroundServices

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.androidcorepractice.R

// - (24-09-2024)
class RunningService: Service() {

    // This function is used to create Bound service
    // onBind() is used when the service is bound to another
    // component (e.g., an activity). Since this is a started
    // service (not bound), it returns null.
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    // This is triggered whenever an another component sends an intent to this
    // running service
    // This method is triggered when the service is started by startService().
    // The service reads the action from the Intent (intent?.action).
    // If the action is START, the start() method is called to start the service.
    // If the action is STOP, the service is stopped using stopSelf().
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action) {
            Actions.START.toString() -> start()
            Actions.STOP.toString() -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    // The start() method defines the behavior when the service starts.
    // It builds a notification using NotificationCompat.Builder, which
    // will be displayed while the service runs in the foreground
    // (important to keep the service alive).
    private fun start() {
        val notification = NotificationCompat.Builder(this, "running_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Run is active")
            .setContentText("Elapsed time: 00:50")
            .build()

        // startForeground(1, notification) puts the service into the
        // foreground with a notification (Android requires foreground
        // services to display a notification).
        startForeground(1, notification)
    }

    // An enum class defines two possible actions: START and STOP.
    // These actions are used in the onStartCommand() method to control
    // whether the service starts or stops.

    //  the enum class Actions is used to define a fixed set of actions
    //  (START and STOP) that control the service's behavior.
    enum class Actions {
        START, STOP
    }
}






