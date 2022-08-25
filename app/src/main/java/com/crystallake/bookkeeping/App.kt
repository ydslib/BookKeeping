package com.crystallake.bookkeeping

import android.app.Application
import com.crystallake.core.AppInitTool
import com.crystallake.router.Router

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initSdk()
        Router.setUp(this)
    }


    private fun initSdk() {

        AppInitTool.initAllModuleAfterInitSdk(this)
        AppInitTool.initAllModuleAfterAgreementDangerSdk(this)
    }


}