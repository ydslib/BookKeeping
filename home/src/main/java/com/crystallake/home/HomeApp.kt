package com.crystallake.home

import android.app.Application
import android.util.Log
import com.crystallake.core.AppInit
import com.crystallake.core.BaseApplication

class HomeApp : BaseApplication(), AppInit {

    companion object {
        const val TAG = "HomeApp"
    }

    override fun initAllModuleAfterInitSdk(application: Application) {
        Log.d(TAG,"initAllModuleAfterInitSdk")
    }

    override fun initAllModuleAfterAgreementDangerSdk(application: Application) {
        Log.d(TAG,"initAllModuleAfterAgreementDangerSdk")
    }

}