/**
 * Created by : yds
 * Time: 2022-09-16 17:10
 */
package com.crystallake.common.hook;

import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class HookHelper {

    public static void hookOnClickListener(View view) throws Exception{
        // 第一步：反射得到 ListenerInfo 对象
        Method getListenerInfo = view.getClass().getDeclaredMethod("getListenerInfo");
        getListenerInfo.setAccessible(true);
        Object listenerInfo = getListenerInfo.invoke(view);
        // 第二步：得到原始的 OnClickListener事件方法

        Class<?> listenerInfoClz = Class.forName("android.view.View.ListenerInfo");
        Field mOnClickListener = listenerInfoClz.getDeclaredField("mOnClickListener");
        mOnClickListener.setAccessible(true);
        View.OnClickListener originOnClickListener = (View.OnClickListener) mOnClickListener.get(listenerInfo);
        // 第三步：用 Hook代理类 替换原始的 OnClickListener
        View.OnClickListener hookedOnClickListener = new HookedClickListenerProxy(originOnClickListener);
        mOnClickListener.set(listenerInfo,hookedOnClickListener);
    }

}
