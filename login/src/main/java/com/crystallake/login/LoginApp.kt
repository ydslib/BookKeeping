package com.crystallake.login

import android.app.Application
import android.util.Log
import com.crystallake.core.AppInit
import com.crystallake.core.BaseApplication
import com.winit.router.Router

class LoginApp : BaseApplication(), AppInit {

    companion object {
        const val TAG = "LoginApp"
    }

    override fun onCreate() {
        super.onCreate()
        Router.setUp(this)
        println("LoginApp")
    }

    override fun initAllModuleAfterInitSdk(application: Application) {
    }

    override fun initAllModuleAfterAgreementDangerSdk(application: Application) {
        Log.d(TAG, "initAllModuleAfterAgreementDangerSdk")
    }

}