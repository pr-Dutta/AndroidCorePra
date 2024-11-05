package com.example.androidcorepractice.Context

import android.app.Application
import android.content.Context

class MyApplication: Application() {

    // - (13-09-2024)
    // This application also have lifecycle like an Activity
//    override fun onCreate() {
//        super.onCreate()
//        val myContext: Context = this
//    }
}

// - (13-09-2024)
// Difference between Activity and Application Context
// -> Each context has it's specific lifetime.
// Activity context is alive as long as the activity does.
// And the Application (Your App) context is alive as long as the application does.
// It has a longer lifetime.