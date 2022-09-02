package com.crystallake.mine

import com.crystallake.base.activity.DataBindingActivity
import com.crystallake.base.config.DataBindingConfig
import com.crystallake.base.vm.BaseViewModel
import com.crystallake.mine.databinding.ActivitySettingBinding
import com.crystallake.router.annotation.Route
import com.crystallake.router.api.Router

@Route("mine.SettingActivity")
class SettingActivity : DataBindingActivity<ActivitySettingBinding, BaseViewModel>() {


    override fun initDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_setting)
    }

    override fun initData() {
        super.initData()
        mBinding?.settingTest?.setOnClickListener {
            Router.getInstance().build("login.LoginActivity").apply {
                bundle?.putString("test", "test")
            }.navigation(this)
        }
    }
}