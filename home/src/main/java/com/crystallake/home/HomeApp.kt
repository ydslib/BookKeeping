package com.crystallake.home

import android.app.Application
import android.util.Log
import com.crystallake.core.AppInit
import com.crystallake.core.BaseApplication
import com.winit.router.Router

class HomeApp : BaseApplication(), AppInit {

    companion object {
        const val TAG = "HomeApp"
    }

    override fun onCreate() {
        super.onCreate()
        Router.setUp(this)
        println("HomeApp")
    }

    override fun initAllModuleAfterInitSdk(application: Application) {

    }

    override fun initAllModuleAfterAgreementDangerSdk(application: Application) {
        Log.d(TAG, "initAllModuleAfterAgreementDangerSdk")
    }

}