package com.crystallake.main.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.crystallake.home.fragment.HomeFragmentProvider
import java.util.*

class MainAdapter(var activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        val loader = ServiceLoader.load(HomeFragmentProvider::class.java)
        val homeFragment = loader.first().createHomeFragment();

        return when (position) {
            0 -> homeFragment
            else -> homeFragment
        }
    }
}