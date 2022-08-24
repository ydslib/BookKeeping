package com.crystallake.login

import android.app.Application
import android.util.Log
import com.crystallake.core.AppInit
import com.crystallake.core.BaseApplication
import com.crystallake.router.Router

class LoginApp : BaseApplication(), AppInit {

    companion object {
        const val TAG = "LoginApp"
    }

    override fun onCreate() {
        super.onCreate()
        Router.setUp(this)
    }

    override fun initAllModuleAfterInitSdk(application: Application) {
        Router.getInstance().register("/login/LoginActivity", LoginActivity::class.java)
    }

    override fun initAllModuleAfterAgreementDangerSdk(application: Application) {
        Log.d(TAG, "initAllModuleAfterAgreementDangerSdk")
    }

}