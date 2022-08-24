package com.crystallake.router.facade

open class RouteMeta(
    var type: RouteType? = null,
    var destination: Class<*>? = null,
    var path: String? = null,
    var group: String? = null
)