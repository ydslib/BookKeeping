package com.crystallake.router.api.template

import com.crystallake.router.api.template.IRouteGroup

interface IRouteRoot {
    fun loadInto(routes: MutableMap<String, Class<out IRouteGroup>>)
}