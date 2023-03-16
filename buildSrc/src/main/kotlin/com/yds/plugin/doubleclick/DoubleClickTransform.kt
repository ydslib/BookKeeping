package com.yds.plugin.doubleclick

import com.yds.plugin.base.BaseTransform
import com.yds.plugin.utils.hasAnnotation
import com.yds.plugin.utils.isStatic
import org.objectweb.asm.ClassReader
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.MethodNode

class DoubleClickTransform(val config: DoubleClickConfig) : BaseTransform(){


    companion object{

        private const val ViewDescriptor = "Landroid/view/View;"

        private const val OnClickViewMethodDescriptor = "(Landroid/view/View;)V"

        private const val ButterKnifeOnClickAnnotationDesc = "Lbutterknife/OnClick;"

        private val MethodNode.onlyOneViewParameter: Boolean
            get() = desc == OnClickViewMethodDescriptor

        private fun MethodNode.hasCheckViewAnnotation(config: DoubleClickConfig): Boolean {
            return hasAnnotation(config.formatCheckViewOnClickAnnotation)
        }

//        private fun MethodNode.hasUncheckViewOnClickAnnotation(config: DoubleClickConfig): Boolean {
//            return hasAnnotation(config.formatUncheckViewOnClickAnnotation)
//        }

        private fun MethodNode.hasButterKnifeOnClickAnnotation(): Boolean {
            return hasAnnotation(ButterKnifeOnClickAnnotationDesc)
        }

    }


    override fun modifyClass(byteArray: ByteArray): ByteArray? {
        val classReader = ClassReader(byteArray)
        val classNode = ClassNode()
        classReader.accept(classNode,ClassReader.EXPAND_FRAMES)
        val methods = classNode.methods
        if (!methods.isNullOrEmpty()) {
            val shouldHookMethodList = mutableSetOf<String>()
//            for (methodNode in methods){
//                if (methodNode.isStatic||
//                        )
//            }
        }
        return null
    }

    override fun isIncremental(): Boolean {
        return false
    }


}