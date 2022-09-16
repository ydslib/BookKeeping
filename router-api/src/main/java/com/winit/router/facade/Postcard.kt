package com.winit.router.facade

import android.content.Context
import android.net.Uri
import android.os.Bundle
import com.winit.router.Router
import com.winit.router.annotation.RouteMeta
import com.winit.router.facade.callback.NavigationCallback
import com.winit.router.template.IProvider

class Postcard : RouteMeta {

    var bundle: Bundle? = null
    var context: Context? = null
    var uri: Uri? = null
    var flags: Int = 0
    var action: String? = null
    var enterAnim: Int = -1
    var exitAnim: Int = -1
    var optionBundle: Bundle? = null
    var provider: IProvider? = null

    constructor() : this(null, null)
    constructor(path: String?, group: String?) : this(path, group, null, null)

    constructor(path: String?, group: String?, uri: Uri?, bundle: Bundle?) : super() {
        this.path = path
        this.group = group
        this.uri = uri
        this.bundle = bundle ?: Bundle()
    }


    fun navigation(mContext: Context? = null, callback: NavigationCallback? = null): Any? {
        return Router.getInstance().navigation(mContext, this, -1, callback)
    }


}