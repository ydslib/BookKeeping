package com.winit.router.facade.callback

import com.winit.router.facade.Postcard

interface NavigationCallback {
    /**
     * Callback when find the destination.
     */
    fun onFound(postcard: Postcard?)

    /**
     * Callback after lose your way.
     */
    fun onLost(postcard: Postcard?)

    /**
     * Callback after navigation.
     */
    fun onArrival(postcard: Postcard?)

    /**
     * Callback on interrupt.
     */
    fun onInterrupt(postcard: Postcard?)
}