package com.yds.plugin.doubleclick

class DoubleClickConfig(
    private val doubleCheckClass:String = "com.crystallake.common.ext.ClickHelper",
    val doubleCheckMethodName:String = "canClick",
    val doubleCheckMethodDescriptor:String = "(Landroid/view/View;)Z",
    private val checkViewOnClickAnnotation: String = "com.crystallake.common.hook.SingleClick",
    val hookPointList: List<DoubleClickHookPoint> = extraHookPoints

) {
    val formatDoubleCheckClass:String
    get() = doubleCheckClass.replace(".","/")

    val formatCheckViewOnClickAnnotation:String
    get() = "L" + checkViewOnClickAnnotation.replace(".","/")


}

data class DoubleClickHookPoint(
    val interfaceName:String,
    val methodName:String,
    val methodSign:String
)

private val extraHookPoints = listOf(
    DoubleClickHookPoint(
        interfaceName = "android/view/View\$OnClickListener",
        methodName = "onClick",
        methodSign = "onClick(Landroid/view/View;)V"
    )
)