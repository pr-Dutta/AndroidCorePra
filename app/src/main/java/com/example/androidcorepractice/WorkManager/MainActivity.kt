package com.example.androidcorepractice.WorkManager

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Space
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import coil.compose.AsyncImage
import com.example.androidcorepractice.R
import com.example.androidcorepractice.ui.theme.AndroidCorePracticeTheme

class MainActivity : AppCompatActivity() {

    private lateinit var workManager: WorkManager
    private val viewModel by viewModels<PhotoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        workManager = WorkManager.getInstance(applicationContext)
        setContent {
            AndroidCorePracticeTheme {
                val workerResult = viewModel.workId?.let { id->
                    workManager.getWorkInfoByIdLiveData(id).observeAsState().value
                }
                LaunchedEffect(key1 = workerResult?.outputData) {
                    if (workerResult?.outputData != null) {
                        val filePath = workerResult.outputData.getString(
                            PhotoCompressionWorker.KEY_RESULT_PATH
                        )
                        filePath?.let {
                            val bitmap = BitmapFactory.decodeFile(it)
                            viewModel.updateCompressedBitmap(bitmap)
                        }
                    }
                }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    viewModel.uncompressedUri?.let {
                        Text("Uncompressed photo:")
                        AsyncImage(model = it, contentDescription = null)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    viewModel.compressedBitmap?.let {
                        Text("Compressed photo:")
                        Image(bitmap = it.asImageBitmap(), contentDescription = null)
                    }
                }

            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
        } else {
            intent.getParcelableExtra(Intent.EXTRA_STREAM)
        } ?: return
        viewModel.updateUncompressedUri(uri)

//        val request = OneTimeWorkRequestBuilder<PhotoCompressionWorker>()
//            .setInputData(
//                workDataOf(
//                    PhotoCompressionWorker.KEY_CONTENT_URI to uri.toString(),
//                    PhotoCompressionWorker.KEY_COMPRESSION_THRESHOLD to 1024 * 20L
//                )
//            )
//            .setConstraints(Constraints(
//                requiresStorageNotLow = true
//            ))
//            .build()
//        viewModel.updateWorkId(request.id)
//        workManager.enqueue(request)
    }
}





