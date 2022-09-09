package com.crystallake.main


import com.crystallake.base.activity.DataBindingActivity
import com.crystallake.base.config.DataBindingConfig
import com.crystallake.base.vm.BaseViewModel
import com.crystallake.main.databinding.ActivityMainBinding
import com.winit.router.Router

class MainActivity : DataBindingActivity<ActivityMainBinding, BaseViewModel>() {

    override fun initDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_main)
    }

    override fun initData() {
        super.initData()
        mBinding?.jump?.setOnClickListener {
            Router.getInstance().build("mine.SettingActivity").navigation(this)
        }
    }

}