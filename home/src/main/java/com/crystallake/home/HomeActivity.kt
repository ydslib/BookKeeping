package com.crystallake.home

import com.crystallake.base.activity.DataBindingActivity
import com.crystallake.base.config.DataBindingConfig
import com.crystallake.base.vm.BaseViewModel
import com.crystallake.home.databinding.ActivityHomeBinding
import com.crystallake.home.fragment.HomeFragment

class HomeActivity : DataBindingActivity<ActivityHomeBinding, BaseViewModel>() {


    override fun initDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_home)
    }

    override fun initData() {
        super.initData()

        supportFragmentManager.beginTransaction().replace(R.id.homeContainer, HomeFragment())
            .commit()
    }


}