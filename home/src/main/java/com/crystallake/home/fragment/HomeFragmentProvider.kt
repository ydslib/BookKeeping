package com.crystallake.home.fragment

import android.content.Context
import androidx.fragment.app.Fragment
import com.crystallake.common.interfaces.FragmentProvider
import com.crystallake.config.consts.RouterPath
import com.winit.router.annotation.Route
import com.winit.router.template.IProvider

@Route(RouterPath.HOME_PROVIDER_HOME)
class HomeFragmentProvider : FragmentProvider {

    override fun init(context: Context?) {
        println("HomeFragmentPro")
    }

    override fun createFragment(): Fragment {
        return HomeFragment()
    }
}