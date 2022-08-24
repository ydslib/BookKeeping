package com.crystallake.config

import android.R
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.InsetDrawable
import android.graphics.drawable.StateListDrawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.IntDef
import androidx.databinding.BindingAdapter

object DrawablesBindingAdapter {
    private const val TAG = "Drawables"

    private const val INVALID = 0
    private val TMP_PADDING = IntArray(4)

    @BindingAdapter(
        value = ["drawable_solidColor",
            "drawable"],
        requireAll = false
    )
    @JvmStatic
    fun setViewBackground(
        view: View,
        solidColor: Int?,
        drawable: Drawable?
    ) {
        val defaultDrawable = drawable
            ?: create(
                solidColor,
            )
        view.background = defaultDrawable
    }

    fun create(
        @ColorInt solidColor: Int?,
    ): Drawable? {
        val drawable = GradientDrawable()
        solidColor?.let {
            drawable.setColor(solidColor)
        }
        return drawable
    }

    private fun dip2px(dipValue: Float?): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return ((dipValue ?: 0f) * scale + .5f).toInt()
    }


    @IntDef(ShapeMode.RECTANGLE, ShapeMode.OVAL, ShapeMode.LINE, ShapeMode.RING)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class ShapeMode {
        companion object {
            const val RECTANGLE = GradientDrawable.RECTANGLE
            const val OVAL = GradientDrawable.OVAL

            /**
             * 画线时，有几点特性必须要知道的：
             * 1. 只能画水平线，画不了竖线；
             * 2. 线的高度是通过stroke的android:width属性设置的；
             * 3. size的android:height属性定义的是整个形状区域的高度；
             * 4. size的height必须大于stroke的width，否则，线无法显示；
             * 5. 线在整个形状区域中是居中显示的；
             * 6. 线左右两边会留有空白间距，线越粗，空白越大；
             * 7. 引用虚线的view需要添加属性android:layerType，值设为"software"，否则显示不了虚线。
             */
            const val LINE = GradientDrawable.LINE
            const val RING = GradientDrawable.RING
        }
    }

    @IntDef(GradientType.LINEAR, GradientType.RADIAL, GradientType.SWEEP)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class GradientType {
        companion object {
            const val LINEAR = 0
            const val RADIAL = 1
            const val SWEEP = 2
        }
    }

    @IntDef(
        Orientation.TOP_BOTTOM,
        Orientation.TR_BL,
        Orientation.RIGHT_LEFT,
        Orientation.BR_TL,
        Orientation.BOTTOM_TOP,
        Orientation.BL_TR,
        Orientation.LEFT_RIGHT,
        Orientation.TL_BR
    )
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class Orientation {
        companion object {
            const val TOP_BOTTOM = 0
            const val TR_BL = 1
            const val RIGHT_LEFT = 2
            const val BR_TL = 3
            const val BOTTOM_TOP = 4
            const val BL_TR = 5
            const val LEFT_RIGHT = 6
            const val TL_BR = 7
        }
    }

    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    @Target(
        AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD
    )
    internal annotation class DP

    class ProxyDrawable : StateListDrawable() {
        var originDrawable: Drawable? = null
            private set

        override fun addState(stateSet: IntArray, drawable: Drawable) {
            if (stateSet != null && stateSet.size == 1 && stateSet[0] == 0) {
                originDrawable = drawable
            }
            super.addState(stateSet, drawable)
        }
    }
}