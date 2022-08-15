package com.crystallake.core

import android.app.Application
import android.util.Log

abstract class BaseApplication : Application() {

    companion object {
        const val TAG = "BaseApplication"
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
    }
}