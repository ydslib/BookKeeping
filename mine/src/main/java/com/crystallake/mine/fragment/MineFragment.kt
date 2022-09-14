package com.crystallake.mine.fragment

import android.os.Bundle
import com.crystallake.base.config.DataBindingConfig
import com.crystallake.base.fragment.DataBindingFragment
import com.crystallake.base.vm.BaseViewModel
import com.crystallake.mine.R
import com.crystallake.mine.databinding.FragmentMineBinding
import com.winit.router.annotation.Route

@Route("mine.MineFragment")
class MineFragment : DataBindingFragment<FragmentMineBinding, BaseViewModel>() {

    override fun createObserver() {

    }

    override fun initView(savedInstanceState: Bundle?) {
        mBinding?.text?.text = arguments?.getString("test") ?: "测试"
    }

    override fun lazyLoadData() {

    }

    override fun initDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_mine)
    }
}