package com.winit.router.core

import com.winit.router.annotation.RouteMeta
import com.winit.router.template.IProvider
import com.winit.router.template.IRouteGroup

class Warehouse {

    companion object {

        @JvmStatic
        val routes: MutableMap<String, RouteMeta> = mutableMapOf()

        val groupsIndex: MutableMap<String, Class<out IRouteGroup>> = mutableMapOf()

        val providersIndex: MutableMap<String, RouteMeta> = mutableMapOf()
        val providers: MutableMap<Class<*>?, IProvider?> = mutableMapOf()

    }
}