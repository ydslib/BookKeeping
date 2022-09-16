package com.crystallake.core

import java.util.ServiceLoader


object ServiceLoaderManager {

    fun <T> getService(clazz: Class<T>):T?{
        val loader = ServiceLoader.load(clazz)
        val iterator = loader.iterator()
        return if (iterator.hasNext()){
            iterator.next()
        }else{
            null
        }
    }
}