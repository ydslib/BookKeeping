package com.crystallake.router.api.core

import com.crystallake.router.annotation.RouteMeta
import com.crystallake.router.api.template.IRouteGroup

class Warehouse {

    companion object {

        @JvmStatic
        val routes: MutableMap<String, RouteMeta> = mutableMapOf()

        val groupsIndex: MutableMap<String, Class<out IRouteGroup>> = mutableMapOf()

    }
}