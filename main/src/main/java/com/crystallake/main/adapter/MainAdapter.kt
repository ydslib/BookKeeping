package com.crystallake.main.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.crystallake.common.interfaces.FragmentProvider
import com.crystallake.config.consts.RouterPath
import com.winit.router.Router

class MainAdapter(var activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {

        val homeFragmentProvider = Router.getInstance().build(RouterPath.PROVIDER_HOME).navigation()
        val homeFragment =
            (homeFragmentProvider as? FragmentProvider)?.createFragment() ?: Fragment()

        val statisticsFragmentProvider =
            Router.getInstance().build(RouterPath.PROVIDER_STATISTICS).navigation()
        val statisticsFragment =
            (statisticsFragmentProvider as? FragmentProvider)?.createFragment() ?: Fragment()

        return when (position) {
            0 -> homeFragment
            1 -> statisticsFragment
            else -> homeFragment
        }
    }
}