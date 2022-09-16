package com.winit.router.compiler.utils

object Consts {


    const val ROUTE_PACKAGE = "com.winit.router.annotation"

    const val ANNOTATION_TYPE_ROUTE = "$ROUTE_PACKAGE.Route"

    const val TEMPLATE_PACKAGE = "com.winit.router.template"

    const val ACTIVITY = "android.app.Activity"
    const val FRAGMENTX = "androidx.fragment.app.Fragment"
    const val FRAGMENT = "android.app.Fragment"

    const val DOT = "."

    const val IROUTE_GROUP = TEMPLATE_PACKAGE + DOT + "IRouteGroup"
    const val ITROUTE_ROOT = TEMPLATE_PACKAGE + DOT + "IRouteRoot"
    const val IPROVIDER_GROUP = TEMPLATE_PACKAGE + DOT + "IProviderGroup"
    const val IPROVIDER = TEMPLATE_PACKAGE + DOT + "IProvider"

    const val METHOD_LOAD_INTO = "loadInto"

    const val PACKAGE_OF_GENERATE_FILE = "com.winit.router.generate"

    const val SDK_NAME = "Router"
    const val SEPARATOR = "$$"
    const val SUFFIX_ROOT = "Root"
    const val SUFFIX_GROUP = "Group"

    const val KEY_MODULE_NAME = "ROUTER_MODULE_NAME"

    const val NAME_OF_ROOT = SDK_NAME + SEPARATOR + SUFFIX_ROOT + SEPARATOR

    const val NAME_OF_GROUP = SDK_NAME + SEPARATOR + SUFFIX_GROUP + SEPARATOR

    const val NAME_OF_PROVIDER = SDK_NAME + SEPARATOR + "Providers"
}