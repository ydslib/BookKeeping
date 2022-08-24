package com.crystallake.router.core

import com.crystallake.router.facade.RouteMeta

class Warehouse {

    companion object {

        @JvmStatic
        val routes: MutableMap<String, RouteMeta> = mutableMapOf()
    }
}