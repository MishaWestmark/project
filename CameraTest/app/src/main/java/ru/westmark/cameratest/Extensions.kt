package ru.westmark.cameratest

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture

fun ListenableFuture<ProcessCameraProvider>.configureCamera(
    previewView: PreviewView,
    lifecycleOwner: LifecycleOwner,
    cameraLens: Int,
    context: Context
): ListenableFuture<ProcessCameraProvider> {
    addListener({
        val preview = Preview.Builder()
            .build()
            .apply {
                setSurfaceProvider(previewView.surfaceProvider)
            }
        get().apply {
            unbindAll()
            bindToLifecycle(
                lifecycleOwner,
                CameraSelector.Builder().requireLensFacing(cameraLens).build(), preview
            )
        }
    }, ContextCompat.getMainExecutor(context))
    return this
}