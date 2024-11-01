package com.example.androidcorepractice.BroadcastsReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings

// - (21-09-2024)
// For Broadcast Receiver
class AirPlaneModeReceiver : BroadcastReceiver() {

    // Every Broadcast receiver then needs a onReceive function which
    // will be triggered when Broadcast is fired

    // When we send a Broadcast We can attach data to it we can
    // attach action to it, in the form of an Intent
//    override fun onReceive(context: Context?, intent: Intent?) {
//        if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
//            val isTurnedOn = Settings.Global.getInt(
//                context?.contentResolver,
//                Settings.Global.AIRPLANE_MODE_ON
//            ) != 0
//            println("Is airplane mode enabled? $isTurnedOn")
//        }
//    }
}