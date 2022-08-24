package com.crystallake.router

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.crystallake.router.core.Warehouse
import com.crystallake.router.exception.HandlerException
import com.crystallake.router.exception.NoRouteFoundException
import com.crystallake.router.facade.Postcard
import com.crystallake.router.facade.RouteType
import com.crystallake.router.facade.callback.NavigationCallback

class Router {

    private constructor()

    companion object {

        @SuppressLint("StaticFieldLeak")
        private var mContext: Context? = null

        private const val TAG = "Router"

        fun getInstance() = Holder.instance

        fun setUp(application: Application) {
            mContext = application
        }
    }


    private val handler: Handler by lazy {
        Handler(Looper.getMainLooper())
    }


    fun register(routePath: String, clazz: Class<*>) {

        if (!Warehouse.routes.containsKey(routePath)) {
            val postcard = build(routePath)
            postcard.destination = clazz
            Warehouse.routes[routePath] = postcard

            if (Activity::class.java.isAssignableFrom(clazz)) {
                postcard.type = RouteType.ACTIVITY
            } else if (Fragment::class.java.isAssignableFrom(clazz) || android.app.Fragment::class.java.isAssignableFrom(
                    clazz
                )
            ) {
                postcard.type = RouteType.FRAGMENT
            }
        }
    }

    fun build(path: String?): Postcard {
        if (path.isNullOrEmpty()) {
            throw HandlerException(TAG + "Parameter is invalid!")
        } else {
            return build(path, extractGroup(path))
        }
    }

    fun build(uri: Uri?): Postcard {
        if (uri == null || uri.toString().isEmpty()) {
            throw HandlerException(TAG + "Parameter is invalid!")
        } else {
            return Postcard(uri.path, extractGroup(uri.path), uri)
        }
    }

    fun build(path: String?, group: String?): Postcard {
        if (path.isNullOrEmpty() || group.isNullOrEmpty()) {
            throw HandlerException(TAG + "Parameter is invalid!");
        } else {
            return Postcard(path = path, group = group)
        }
    }

    private fun extractGroup(path: String?): String? {
        if (path.isNullOrEmpty() || !path.startsWith("/")) {
            throw HandlerException(TAG + "Extract the default group failed, the path must be start with '/' and contain more than 2 '/'!")
        }
        try {
            val defaultGroup = path.substring(1, path.indexOf("/", 1))
            if (defaultGroup.isEmpty()) {
                throw HandlerException(TAG + "Extract the default group failed! There's nothing between 2 '/'!")
            } else {
                return defaultGroup
            }
        } catch (e: Exception) {
            return null
        }
    }

    fun completion(postcard: Postcard?) {
        if (postcard == null) {
            throw NoRouteFoundException("No postcard!")
        }
        val routeMeta = Warehouse.routes[postcard.path]

        if (routeMeta != null) {
            postcard.destination = routeMeta.destination
            postcard.type = routeMeta.type


            when (postcard.type) {
                RouteType.FRAGMENT -> {
                    //
                }
                else -> {
                    //
                }
            }
        }

    }

    private fun runOnUiThread(runnable: Runnable) {
        if (Looper.getMainLooper().thread != Thread.currentThread()) {
            handler.post(runnable)
        } else {
            runnable.run()
        }
    }

    private fun startActivity(
        requestCode: Int,
        currentContext: Context,
        intent: Intent,
        postcard: Postcard,
        callback: NavigationCallback?
    ) {
        if (requestCode >= 0) {
            if (currentContext is Activity) {
                ActivityCompat.startActivityForResult(
                    currentContext,
                    intent,
                    requestCode,
                    postcard.bundle
                )
            } else {
                Log.w(
                    TAG,
                    "Must use [navigation(activity, ...)] to support [startActivityForResult]"
                )
            }
        } else {
            ActivityCompat.startActivity(currentContext, intent, postcard.bundle)
        }

        callback?.onArrival(postcard)
    }

    fun navigation(
        context: Context?,
        postcard: Postcard?,
        requestCode: Int?,
        callback: NavigationCallback? = null
    ): Any? {
        val context = context ?: mContext
        postcard?.context = context
        try {
            completion(postcard)
        } catch (e: Exception) {
            callback?.onLost(postcard)
            return null
        }
        callback?.onFound(postcard)
        return navigation(postcard, requestCode, callback)
    }

    private fun navigation(
        postcard: Postcard?,
        requestCode: Int?,
        callback: NavigationCallback?
    ): Any? {
        val context = postcard?.context

        when (postcard?.type) {
            RouteType.ACTIVITY -> {
                val intent = Intent(context, postcard.destination)
                postcard.bundle?.let { intent.putExtras(it) }

                if (postcard.flags != 0) {
                    intent.flags = postcard.flags
                }

                if (context !is Activity) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }

                val action = postcard.action
                if (!action.isNullOrEmpty()) {
                    intent.action = action
                }
                runOnUiThread {
                    if (context != null) {
                        startActivity(
                            requestCode ?: 0,
                            currentContext = context,
                            intent,
                            postcard,
                            callback
                        )
                    }

                }
            }
            RouteType.FRAGMENT -> {
                val fragmentMeta = postcard.destination
                try {
                    val instance = fragmentMeta?.getConstructor()?.newInstance()
                    if (instance is Fragment) {
                        instance.arguments = postcard.bundle
                    } else if (instance is android.app.Fragment) {
                        instance.arguments = postcard.bundle
                    }
                    return instance
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            else -> {
                return null
            }
        }

        return null

    }

    class Holder {
        companion object {
            val instance = Router()
        }
    }
}