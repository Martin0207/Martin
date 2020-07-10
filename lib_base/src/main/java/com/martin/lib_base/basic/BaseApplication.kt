package com.martin.lib_base.basic

import android.app.Application
import android.content.Context
import com.martin.lib_base.impl.ModuleManagerImpl
import com.martin.lib_base.interfaces.IModuleManager
import com.martin.lib_base.BaseLib
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * @Author: martin
 * @CreateDate: 2019/8/16 15:32
 * @Description: 必须要继承或实现该类功能，否则无法实现组件化
 */
class BaseApplication : Application() {

    private val mModuleManager: IModuleManager by lazy {
        ModuleManagerImpl(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        base?.let {
            mModuleManager.attachBaseContext(it)
        }
    }

    override fun onCreate() {
        super.onCreate()
        mModuleManager.onCreate(this)
        startKoin {
            androidContext(this@BaseApplication)
            androidLogger()
            androidFileProperties()
            modules(BaseLib.modules)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        mModuleManager.onTerminate(this)
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }
}