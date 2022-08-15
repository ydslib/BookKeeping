package com.crystallake.core

import android.app.Application

interface AppInit {
    /**
     *
     * 初始化各个module在同意隐私协议之前
     * @param application
     */
    fun initAllModuleAfterInitSdk(application: Application)

    /**
     *
     * 初始化各个module在同意隐私协议之后
     * @param application
     */
    fun initAllModuleAfterAgreementDangerSdk(application: Application)
}
