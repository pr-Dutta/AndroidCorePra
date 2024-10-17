package com.example.androidcorepractice.ViewModel

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidcorepractice.ui.theme.AndroidCorePracticeTheme

class MainActivity : AppCompatActivity() {

    // This is the android specific viewModel instance
    // this is from outside the Composable
//    private val viewModel by viewModels<ContactsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidCorePracticeTheme {
                // this viewModel is from inside compose

                // - (12-09-2024)
                // The viewModel() function is used to obtain a ViewModel
                // instance scoped to the current Composable function.
                val viewModel = viewModel<ContactsViewModel>(
                    // The factory parameter is used to provide a custom
                    // ViewModelProvider.Factory to create the ViewModel.

                    // A factory is necessary when a ViewModel requires
                    // parameters (like helloWorld in this case) that the
                    // default constructor cannot provide.
                    factory = object : ViewModelProvider.Factory {
                        // This overridden create function is part of the
                        // ViewModelProvider.Factory interface.

                        // It is responsible for creating the ViewModel instance.
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {

                            // Here, a new instance of ContactsViewModel is
                            // created with the helloWorld parameter set to
                            // "Hello world!".
                            return ContactsViewModel(
                                helloWorld = "Hello world!"
                            ) as T
                            // The as T cast is required to match the expected
                        // return type, as create() is a generic function.
                        }
                    }
                )
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = viewModel.backgroundColor
//                ) {
//                    Button(onClick = {
//                        viewModel.changeBackgroundColor()
//                    }) {
//                        Text(text = "Change color")
//                    }
//                }
            }
        }
    }
}