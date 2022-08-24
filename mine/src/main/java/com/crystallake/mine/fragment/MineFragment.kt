package com.crystallake.mine.fragment

import android.os.Bundle
import com.crystallake.base.config.DataBindingConfig
import com.crystallake.base.fragment.DataBindingFragment
import com.crystallake.base.vm.BaseViewModel
import com.crystallake.mine.R
import com.crystallake.mine.databinding.FragmentMineBinding

class MineFragment : DataBindingFragment<FragmentMineBinding, BaseViewModel>() {

    override fun createObserver() {

    }

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun lazyLoadData() {
    }

    override fun initDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_mine)
    }
}