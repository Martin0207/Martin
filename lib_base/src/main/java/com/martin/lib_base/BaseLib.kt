package com.martin.lib_base

import org.koin.core.module.Module

/**
 * @author：孟凡华
 * @date：2019/9/6 11:29
 */
object BaseLib {

    /**
     * koin初始化的Modules
     */
    val modules = arrayListOf<Module>()

    /**
     * 是否为debug模式
     */
    val debug = BuildConfig.apkType == "debug"

}