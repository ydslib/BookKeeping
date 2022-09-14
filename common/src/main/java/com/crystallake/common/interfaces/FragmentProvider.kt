package com.crystallake.common.interfaces

import androidx.fragment.app.Fragment

interface FragmentProvider {
    fun createFragment(): Fragment
}