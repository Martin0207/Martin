package com.martin.lib_base.interfaces

import android.app.Application
import android.content.Context
import org.koin.core.KoinComponent

interface IModuleManager:KoinComponent {

    /**
     * 在onCreate之前调用,一般用于解决方法数过多的问题
     */
    fun attachBaseContext(base: Context)

    fun onCreate(application: Application)

    fun onTerminate(application: Application)

}