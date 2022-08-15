package com.crystallake.main

import android.app.Application
import android.util.Log
import com.crystallake.core.AppInit
import com.crystallake.core.BaseApplication

class MainApp : BaseApplication(), AppInit {

    companion object {
        const val TAG = "MainApp"
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG,"onCreate")
    }

    override fun initAllModuleAfterInitSdk(application: Application) {
        Log.d(TAG,"initAllModuleAfterInitSdk")
    }

    override fun initAllModuleAfterAgreementDangerSdk(application: Application) {
        Log.d(TAG,"initAllModuleAfterAgreementDangerSdk")
    }
}