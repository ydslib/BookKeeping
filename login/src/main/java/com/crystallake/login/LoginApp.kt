package com.crystallake.login

import android.app.Application
import android.util.Log
import com.crystallake.core.AppInit
import com.crystallake.core.BaseApplication

class LoginApp : BaseApplication(), AppInit {

    companion object {
        const val TAG = "LoginApp"
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG,"LoginApp")
    }

    override fun initAllModuleAfterInitSdk(application: Application) {
        Log.d(TAG,"initAllModuleAfterInitSdk")
    }

    override fun initAllModuleAfterAgreementDangerSdk(application: Application) {
        Log.d(TAG,"initAllModuleAfterAgreementDangerSdk")
    }

}