package com.crystallake.main


import androidx.core.content.ContextCompat
import com.crystallake.base.config.DataBindingConfig
import com.crystallake.common.CommonActivity
import com.crystallake.main.adapter.MainAdapter
import com.crystallake.main.databinding.ActivityMainBinding
import com.crystallake.main.vm.MainViewModel

/**
 *  d9e1e7
 *  63c4d0
 */
class MainActivity : CommonActivity<ActivityMainBinding, MainViewModel>() {

    override fun initDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_main)
            .addBindingParam(BR.click, ClickProxy())
            .addBindingParam(BR.adapter,MainAdapter(this))
    }

    override fun initData() {
        super.initData()

    }

    inner class ClickProxy {
        var lastIndex = -1
        var currentIndex = 0
        fun goHome() {
            lastIndex = currentIndex
            currentIndex = 0
            mBinding?.viewPager?.setCurrentItem(currentIndex, false)
            if (currentIndex != lastIndex) {
                select(position = currentIndex)
            }
        }

        fun goStatistics() {
            lastIndex = currentIndex
            currentIndex = 1

        }

        fun select(position: Int) {
            mBinding?.bottomNavigationBar?.homeIv?.setImageResource(if (position == 0) R.drawable.ic_main_home else R.drawable.ic_main_home_default)
            mBinding?.bottomNavigationBar?.homeTv?.setTextColor(
                if (position == 0) ContextCompat.getColor(
                    this@MainActivity,
                    R.color.common_63c4d0
                ) else ContextCompat.getColor(this@MainActivity, R.color.common_66212121)
            )

            mBinding?.bottomNavigationBar?.statisticsIv?.setImageResource(if (position == 1) R.drawable.ic_main_statistics else R.drawable.ic_main_statistics_default)
            mBinding?.bottomNavigationBar?.homeTv?.setTextColor(
                if (position == 1) ContextCompat.getColor(
                    this@MainActivity, R.color.common_63c4d0
                ) else ContextCompat.getColor(this@MainActivity, R.color.common_66212121)
            )
            mBinding?.bottomNavigationBar?.walletIv?.setImageResource(if (position == 3) R.drawable.ic_main_wallet_default else R.drawable.ic_main_wallet)
            mBinding?.bottomNavigationBar?.walletTv?.setTextColor(
                if (position == 3) ContextCompat.getColor(this@MainActivity, R.color.common_63c4d0)
                else ContextCompat.getColor(this@MainActivity, R.color.common_66212121)
            )

            mBinding?.bottomNavigationBar?.mineIv?.setImageResource(if (position == 4) R.drawable.ic_main_mine else R.drawable.ic_main_mine_default)
            mBinding?.bottomNavigationBar?.mineTv?.setTextColor(
                if (position == 4) ContextCompat.getColor(this@MainActivity, R.color.common_63c4d0)
                else ContextCompat.getColor(this@MainActivity, R.color.common_66212121)
            )
        }
    }


}