package com.westmark.unsplash.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class UnsplashApp : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}