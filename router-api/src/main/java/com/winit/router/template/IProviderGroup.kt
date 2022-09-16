package com.winit.router.template

import com.winit.router.annotation.RouteMeta

interface IProviderGroup {

    fun loadInto(providers: MutableMap<String, RouteMeta>)

}