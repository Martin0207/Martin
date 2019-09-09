package com.martin.lib_base.impl

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.bumptech.glide.Glide
import com.martin.lib_base.retrofit.NetUtil
import com.martin.lib_base.interfaces.IModuleManager
import com.martin.lib_base.BaseLib
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.xuexiang.xui.XUI
import dev.DevUtils
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import timber.log.Timber

/**
 * @author：孟凡华
 * @date：2019/9/6 11:29
 * @sample：以BaseLib为样例
 * 每个Module都可以通过继承[IModuleManager]，实现依据Application声明周期的各种操作
 * 多为Module的个性初始化
 */
class BaseLibModuleManager : IModuleManager {

    override fun attachBaseContext(base: Context) {
        MultiDex.install(base)
    }

    override fun onCreate(application: Application) {
        XUI.init(application)
        XUI.debug(BaseLib.debug)
        //常用开发工具初始化
        DevUtils.init(application.applicationContext)
        //Debug
        if (BaseLib.debug) {
            DevUtils.openLog()
            DevUtils.openDebug()

            Timber.plant(Timber.DebugTree())
            Logger.addLogAdapter(AndroidLogAdapter())
        }
        BaseLib.modules.add(module {
            single { NetUtil.getApi() }
        })
    }

    override fun onTerminate(application: Application) {
        stopKoin()
    }

}