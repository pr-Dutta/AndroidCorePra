package com.example.androidcorepractice

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ImageViewModel : ViewModel() {

    var uri: Uri? by mutableStateOf(null)
        private set
    // It's private set that it can be mutate from within the view-model only

//    fun updateUri(uri: Uri?) {
//        this.uri = uri
//    }
}