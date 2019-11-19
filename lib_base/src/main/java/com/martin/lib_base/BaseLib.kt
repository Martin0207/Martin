package com.martin.lib_base

import org.koin.core.module.Module

/**
 * @author：孟凡华
 * @date：2019/9/6 11:29
 */
object BaseLib {

    /**
     * Koin初始化的Modules
     * 每个Module之间相互独立,所以需要统一处理
     */
    val modules = arrayListOf<Module>()

    /**
     * 是否为debug模式
     */
    val debug = BuildConfig.apkType == "debug"

    /**
     * 应用名称
     * 用于各个地方的命名,例如:数据库名称
     */
    val appName = "martin_frame"

}