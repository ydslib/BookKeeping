package com.crystallake.bookkeeping

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.crystallake.core.AppInitTool

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initSdk()
    }


    private fun initSdk() {
        if (BuildConfig.isDebug) {
            ARouter.openDebug()
            ARouter.openLog()
        }

        AppInitTool.initAllModuleAfterInitSdk(this)
        AppInitTool.initAllModuleAfterAgreementDangerSdk(this)
    }


}