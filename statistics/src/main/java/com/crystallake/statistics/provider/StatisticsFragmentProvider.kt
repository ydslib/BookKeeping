package com.crystallake.statistics.provider

import android.content.Context
import androidx.fragment.app.Fragment
import com.crystallake.common.interfaces.FragmentProvider
import com.crystallake.config.consts.RouterPath
import com.crystallake.statistics.fragment.StatisticsFragment
import com.winit.router.annotation.Route

@Route(RouterPath.PROVIDER_STATISTICS)
class StatisticsFragmentProvider : FragmentProvider {

    override fun createFragment(): Fragment {
        return StatisticsFragment()
    }

    override fun init(context: Context?) {
    }
}