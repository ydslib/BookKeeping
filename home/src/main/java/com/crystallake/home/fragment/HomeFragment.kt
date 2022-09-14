package com.crystallake.home.fragment

import android.os.Bundle
import com.crystallake.base.config.DataBindingConfig
import com.crystallake.base.fragment.DataBindingFragment
import com.crystallake.config.consts.RouterPath
import com.crystallake.home.R
import com.crystallake.home.databinding.FragmentHomeBinding
import com.crystallake.home.vm.HomeViewModel
import com.winit.router.annotation.Route

@Route(RouterPath.HOME_FRAGMENT_HOME)
class HomeFragment : DataBindingFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun createObserver() {
    }

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun lazyLoadData() {
    }

    override fun initDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_home)
    }
}