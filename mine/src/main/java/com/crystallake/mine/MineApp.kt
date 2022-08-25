package com.crystallake.mine

import android.app.Application
import com.crystallake.base.app.BaseApplication
import com.crystallake.core.AppInit
import com.crystallake.router.Router

class MineApp : BaseApplication(), AppInit {

    override fun onCreate() {
        super.onCreate()
        Router.setUp(this)
        println("MineApp")
    }

    override fun initAllModuleAfterInitSdk(application: Application) {
    }

    override fun initAllModuleAfterAgreementDangerSdk(application: Application) {
    }
}