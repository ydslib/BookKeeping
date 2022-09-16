package com.winit.router

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
import com.winit.router.annotation.RouteType
import com.winit.router.core.LogisticsCenter
import com.winit.router.core.Warehouse
import com.winit.router.exception.HandlerException
import com.winit.router.facade.Postcard
import com.winit.router.facade.callback.NavigationCallback


class Router {

    private constructor()

    companion object {

        @SuppressLint("StaticFieldLeak")
        private var mContext: Context? = null

        private const val TAG = "Router"

        fun getInstance() = Holder.instance

        @JvmStatic
        fun setUp(application: Application) {
            mContext = application
            LogisticsCenter.registerRouter(application)
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

    fun build(uri: Uri?): Postcard {
        if (uri == null || uri.toString().isEmpty()) {
            throw HandlerException(TAG + "Parameter is invalid!")
        } else {
            return Postcard(uri.path, extractGroup(uri.path), uri, null)
        }
    }

    fun build(path: String?, group: String? = null): Postcard {
        return if (path.isNullOrEmpty()) {
            throw HandlerException(TAG + "Parameter is invalid!");
        } else {
            if (group.isNullOrEmpty()) {
                build(path, extractGroup(path))
            } else {
                Postcard(path = path, group = group)
            }
        }
    }

    private fun extractGroup(path: String?): String? {
        if (path.isNullOrEmpty() && path?.contains(".") == false) {
            throw HandlerException(TAG + "Extract the default group failed, the path must has 2 element")
        }
        return try {
            val defaultGroup = path?.substring(0, path.indexOf("."))
            if (defaultGroup?.isEmpty() == true) {
                throw HandlerException(TAG + "Extract the default group failed! ")
            } else {
                defaultGroup
            }
        } catch (e: Exception) {
            null
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
                    postcard.optionBundle
                )
            } else {
                Log.w(
                    TAG,
                    "Must use [navigation(activity, ...)] to support [startActivityForResult]"
                )
            }
        } else {
            ActivityCompat.startActivity(currentContext, intent, postcard.optionBundle)
        }

        if (postcard.enterAnim != -1 && postcard.exitAnim != -1 && currentContext is Activity) {
            currentContext.overridePendingTransition(
                postcard.enterAnim,
                postcard.exitAnim
            )
        }

        callback?.onArrival(postcard)
    }

    fun navigation(
        context: Context?,
        postcard: Postcard?,
        requestCode: Int?,
        callback: NavigationCallback? = null
    ): Any? {

        postcard?.context = context ?: mContext
        try {
            LogisticsCenter.completion(mContext, postcard)
        } catch (e: Exception) {
            callback?.onLost(postcard)
            return null
        }
        callback?.onFound(postcard)
        return navigation(postcard, requestCode, callback)
    }

    fun <T> navigation(service: Class<out T>): T? {
        try {
            var postcard = LogisticsCenter.buildProvider(service.name)
            if (postcard == null) {
                postcard = LogisticsCenter.buildProvider(service.simpleName)
            }
            if (postcard == null) {
                return null
            }
            postcard.context = mContext
            LogisticsCenter.completion(mContext, postcard)
            return postcard.provider as? T
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
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
            RouteType.PROVIDER -> {
                return postcard.provider
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