package com.winit.router.core

import android.content.Context
import com.winit.router.Router
import com.winit.router.annotation.RouteType
import com.winit.router.exception.HandlerException
import com.winit.router.exception.NoRouteFoundException
import com.winit.router.facade.Postcard
import com.winit.router.template.IProvider
import com.winit.router.template.IProviderGroup
import com.winit.router.template.IRouteGroup
import com.winit.router.template.IRouteRoot
import com.winit.router.utils.ClassUtils
import com.winit.router.utils.Consts

object LogisticsCenter {

    const val TAG = "LogisticsCenter"

    fun registerRouter(context: Context) {

        try {
            val routerMap =
                ClassUtils.getFileNameByPackageName(
                    context,
                    Consts.ROUTER_ROOT_PACKAGE
                )

            routerMap?.forEach { className ->
                if (className.startsWith(Consts.ROUTER_ROOT_PACKAGE + Consts.DOT + Consts.SDK_NAME + Consts.SEPARATOR + Consts.SUFFIX_ROOT)) {
                    ((Class.forName(className).getConstructor()
                        .newInstance()) as IRouteRoot).loadInto(Warehouse.groupsIndex)
                } else if (className.startsWith(Consts.ROUTER_ROOT_PACKAGE + Consts.DOT + Consts.SDK_NAME + Consts.SEPARATOR + Consts.SUFFIX_PROVIDERS)) {
                    ((Class.forName(className).getConstructor()
                        .newInstance()) as IProviderGroup).loadInto(Warehouse.providersIndex)
                }
            }
        } catch (e: Exception) {

        }
    }

    @Synchronized
    fun completion(context: Context?, postcard: Postcard?) {
        if (postcard == null) {
            throw NoRouteFoundException("No postcard!")
        }
        val routeMeta = Warehouse.routes[postcard.path]

        if (routeMeta == null) {
            if (!Warehouse.groupsIndex.containsKey(postcard.group)) {
                throw NoRouteFoundException(TAG + "There is no route match the path [" + postcard.path + "], in group [" + postcard.group + "]")
            } else {
                try {
                    addRouteGroupDynamic(postcard.group, null)
                } catch (e: Exception) {

                }
                completion(context, postcard)
            }
        }

        if (routeMeta != null) {
            postcard.destination = routeMeta.destination
            postcard.type = routeMeta.type
            postcard.extra = routeMeta.extra


            when (postcard.type) {
                RouteType.FRAGMENT -> {
                    //
                }
                RouteType.PROVIDER -> {
                    val providerMeta: Class<out IProvider>? =
                        routeMeta.destination as? Class<out IProvider>
                    var instance: IProvider? = Warehouse.providers[providerMeta]
                    if (instance == null) {
                        try {
                            val provider = providerMeta?.getConstructor()?.newInstance()
                            provider?.init(context)
                            Warehouse.providers[providerMeta] = provider
                            instance = provider
                        } catch (e: Exception) {
                            throw HandlerException("Init provider failed!")
                        }
                    }
                    postcard.provider = instance
                }
                else -> {
                    //
                }
            }
        }

    }

    @Synchronized
    fun addRouteGroupDynamic(groupName: String, group: IRouteGroup?) {
        if (Warehouse.groupsIndex.containsKey(groupName)) {
            Warehouse.groupsIndex[groupName]?.getConstructor()?.newInstance()
                ?.loadInto(Warehouse.routes)
            Warehouse.groupsIndex.remove(groupName)
        }
        group?.loadInto(Warehouse.routes)
    }

    fun buildProvider(serviceName: String?): Postcard? {
        val meta = Warehouse.providersIndex[serviceName]
        return if (meta == null) null
        else Postcard(meta.path, meta.group)
    }
}