package com.example.androidcorepractice.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

// this ViewModel create a component, viewModel which will outlive the lifecycle
// of the Screen (in our case it's activity), means viewModel wouldn't get
// destroyed when the activity get destroyed. The viewModel will be destroyed
// when the user actively pops up the activity from its stack (Clicking the
// back button).
class ContactsViewModel(
    val helloWorld: String
): ViewModel() {

    // Inside here we can put all the functions which will be call
    // when a user action performs, like a button click for background
    // color change

    // - (12-09-2024)
    // This mutable state of triggers the UI change when it changes
    // This ViewModel will notify the UI to Update.

    // mutableStateOf() creates it a  Jetpack Compose state.
    var backgroundColor by mutableStateOf(Color.White)
        private set

    fun changeBackgroundColor() {
        backgroundColor = Color.Red
    }
}

// - (12-09-2024)
// Configuration change like Screen rotation, Language change, theme change etc