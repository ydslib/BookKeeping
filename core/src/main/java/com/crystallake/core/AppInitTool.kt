package com.crystallake.core

import android.app.Application
import java.util.*

object AppInitTool {
    private val loader: ServiceLoader<AppInit> by lazy {
        ServiceLoader.load(AppInit::class.java)
    }

    fun initAllModuleAfterInitSdk(application: Application) {
        val mIterator: Iterator<AppInit> = loader.iterator()
        while (mIterator.hasNext()) {
            mIterator.next().initAllModuleAfterInitSdk(application)
        }
    }

    fun initAllModuleAfterAgreementDangerSdk(application: Application) {
        val mIterator: Iterator<AppInit> = loader.iterator()
        while (mIterator.hasNext()) {
            mIterator.next().initAllModuleAfterAgreementDangerSdk(application)
        }
    }
}