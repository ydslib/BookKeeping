package com.winit.router.annotation

enum class RouteType(val id: Int, val className: String) {
    ACTIVITY(0, "android.app.Activity"),
    FRAGMENT(-1, "android.app.Fragment"),//androidx.fragment.app.Fragment
    PROVIDER(2,"com.winit.router.template.IProvider")
}