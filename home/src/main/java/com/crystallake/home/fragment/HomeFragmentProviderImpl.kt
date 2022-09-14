package com.crystallake.home.fragment

import androidx.fragment.app.Fragment

class HomeFragmentProviderImpl : HomeFragmentProvider {
    override fun createHomeFragment(): Fragment {
        return HomeFragment()
    }


}