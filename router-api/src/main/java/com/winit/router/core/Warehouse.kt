package com.winit.router.core

import com.winit.router.annotation.RouteMeta
import com.winit.router.template.IRouteGroup

class Warehouse {

    companion object {

        @JvmStatic
        val routes: MutableMap<String, RouteMeta> = mutableMapOf()

        val groupsIndex: MutableMap<String, Class<out IRouteGroup>> = mutableMapOf()

    }
}