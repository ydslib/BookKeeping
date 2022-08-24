package com.crystallake.router.facade

import android.content.Context
import android.net.Uri
import android.os.Bundle
import com.crystallake.router.Router
import com.crystallake.router.facade.callback.NavigationCallback

class Postcard : RouteMeta {

    var bundle: Bundle? = null
    var uri: Uri? = null
    var context: Context? = null
    var flags: Int = 0
    var action: String? = null


    constructor(
        path: String? = null,
        group: String? = null,
        uri: Uri? = null,
        bundle: Bundle? = null
    ) : super(
        path = path,
        group = group
    ) {
        this.bundle = bundle
        if (this.bundle == null) {
            this.bundle = Bundle()
        }
        this.uri = uri
    }


    fun navigation(mContext: Context? = null, callback: NavigationCallback? = null): Any? {
        return Router.getInstance().navigation(mContext, this, -1, callback)
    }


}