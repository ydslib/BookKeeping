package com.crystallake.common.ext

import android.view.View

object ClickHelper {
    val map = mutableMapOf<View, Long>()
    @JvmStatic
    fun canClick(view: View): Boolean {

        val curTime = System.currentTimeMillis()
        val time = if (map.containsKey(view) && map[view] != null) {
            map[view]!!
        } else {
            0
        }
        map[view] = curTime
        if (curTime - time > 2000) {
            return true
        }
        return false
    }
}