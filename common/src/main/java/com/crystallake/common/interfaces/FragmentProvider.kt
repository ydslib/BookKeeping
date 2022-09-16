package com.crystallake.common.interfaces

import androidx.fragment.app.Fragment
import com.winit.router.template.IProvider

interface FragmentProvider : IProvider {
    fun createFragment(): Fragment
}