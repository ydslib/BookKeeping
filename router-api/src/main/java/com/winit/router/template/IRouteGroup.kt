package com.winit.router.template

import com.winit.router.annotation.RouteMeta

interface IRouteGroup {
    fun loadInto(atlas: MutableMap<String, RouteMeta>)
}