package com.crystallake.mine

import androidx.fragment.app.Fragment
import com.crystallake.base.activity.DataBindingActivity
import com.crystallake.base.config.DataBindingConfig
import com.crystallake.base.vm.BaseViewModel
import com.crystallake.mine.databinding.ActivitySettingBinding
import com.winit.router.annotation.Route
import com.winit.router.Router

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

        mBinding?.fragmentTest?.setOnClickListener {
            val fragment = Router.getInstance().build("mine.MineFragment").apply {
                bundle?.putString("test","test")
            }.navigation() as Fragment
            supportFragmentManager.beginTransaction().add(R.id.container,fragment).commit()
        }
    }
}