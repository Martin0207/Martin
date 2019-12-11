package com.martin.lib_base.impl

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import androidx.room.Room
import com.martin.lib_base.BaseLib
import com.martin.lib_base.dao.DB
import com.martin.lib_base.interfaces.IModuleManager
import com.martin.lib_base.retrofit.NetUtil
import com.orhanobut.logger.Logger
import dev.DevUtils
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
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
        //常用开发工具初始化
        DevUtils.init(application.applicationContext)
        //Debug
        if (BaseLib.debug) {
            DevUtils.openLog()
            DevUtils.openDebug()

            Timber.plant(Timber.DebugTree())
            Logger.addLogAdapter(LogAdapterImpl())
        }
        initModules(application)
    }

    private fun initModules(application: Application) {
        BaseLib.modules.add(module {
            single {
                NetUtil.getApi()
            }
            single {
                application.applicationContext.getSharedPreferences(
                    "${BaseLib.appName}_sp",
                    Context.MODE_PRIVATE
                )
            }
            single { application.applicationContext.resources }
            buildDB(application)
        })
    }

    /**
     * 创建数据库单例
     */
    private fun Module.buildDB(application: Application) {
        single {
            Room.databaseBuilder(
                application.applicationContext,
                DB::class.java,
                "${BaseLib.appName}_database"
            ).build()
        }
    }

    override fun onTerminate(application: Application) {
        stopKoin()
    }

}