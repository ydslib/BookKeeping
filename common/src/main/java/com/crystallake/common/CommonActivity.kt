package com.crystallake.common

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.crystallake.base.activity.DataBindingActivity
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ktx.hasNavigationBar
import com.gyf.immersionbar.ktx.hideStatusBar
import com.gyf.immersionbar.ktx.immersionBar
import com.gyf.immersionbar.ktx.showStatusBar

abstract class CommonActivity<D : ViewDataBinding, VM : ViewModel> : DataBindingActivity<D, VM>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        fullScreen()
        super.onCreate(savedInstanceState)

    }

    private fun fullScreen() {
        immersionBar {
            if (needShowStatus()) {
                showStatusBar()//展示状态栏
                statusBarDarkFont(darkStatus())
                navigationBarColor("#FFFFFF")
                navigationBarDarkIcon(darkStatus())
            } else {
                hideStatusBar()
                fullScreen(true)
            }

            if (hasNavigationBar && isHideBar()) {
                hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR)
            }
        }
    }

    /**
     * 是否隐藏导航栏
     */
    open fun isHideBar() = true

    open fun darkStatus() = true

    open fun needShowStatus() = true


}