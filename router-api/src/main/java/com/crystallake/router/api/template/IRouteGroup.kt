package com.crystallake.router.api.template

import com.crystallake.router.annotation.RouteMeta

interface IRouteGroup {
    fun loadInto(atlas: MutableMap<String, RouteMeta>)
}