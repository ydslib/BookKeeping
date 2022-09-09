package com.winit.router.annotation

@Target(AnnotationTarget.CLASS)
@Retention(value = AnnotationRetention.BINARY)
annotation class Route(vararg val path: String)
