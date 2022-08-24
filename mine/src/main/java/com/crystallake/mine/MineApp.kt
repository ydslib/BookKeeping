package com.crystallake.mine

import android.app.Application
import com.crystallake.base.app.BaseApplication
import com.crystallake.core.AppInit
import com.crystallake.router.Router

class MineApp : BaseApplication(), AppInit {

    override fun onCreate() {
        super.onCreate()
        Router.setUp(this)
    }

    override fun initAllModuleAfterInitSdk(application: Application) {
        Router.getInstance().register("/mine/SettingActivity", SettingActivity::class.java)
    }

    override fun initAllModuleAfterAgreementDangerSdk(application: Application) {
    }
}