package com.crystallake.mine

import com.crystallake.base.config.DataBindingConfig
import com.crystallake.common.CommonActivity
import com.crystallake.mine.databinding.ActivityMineBinding
import com.crystallake.mine.vm.MineViewModel
import com.winit.router.annotation.Route

@Route(path = ["mine.MineActivity", "mine.MineActivity02"])
class MineActivity : CommonActivity<ActivityMineBinding, MineViewModel>() {



    override fun initDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_mine)
    }
}