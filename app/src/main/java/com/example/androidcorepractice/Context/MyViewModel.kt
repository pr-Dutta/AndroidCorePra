package com.example.androidcorepractice.Context

import android.content.Context
import androidx.lifecycle.ViewModel

// - (13-09-2024)
// This viewmodel lifecycle will out leave the lifecycle of the activity
// But it still access the resources of the activity which should normally
// be destroyed after config change. They are not destroyed. Simply because
// the garbage collector sees tha the context object is still needed in the viewModel
// class so i better not collect it. But since the activity is destroyed which this
// context was connected to it can't be used anymore. So its always recommended
// not to store activity context outside the particular activity. at least not
// in components where the lifecycle is different then activity.
class MyViewModel: ViewModel() {

    var context: Context? = null
}