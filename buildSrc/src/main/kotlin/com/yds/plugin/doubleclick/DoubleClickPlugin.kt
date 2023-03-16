package com.yds.plugin.doubleclick

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class DoubleClickPlugin:Plugin<Project> {
    override fun apply(target: Project) {
        val config = DoubleClickConfig()
        val appExtension:AppExtension = target.extensions.getByType()
//        appExtension.registerTransform()
    }
}