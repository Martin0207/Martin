package com.martin.lib_base.impl

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import com.martin.lib_base.interfaces.IModuleManager
import dev.utils.common.ReflectUtils

class ModuleManagerImpl @SuppressLint("CheckResult") constructor(context: Context) :
    IModuleManager {

    private val mModuleManagers = ArrayList<IModuleManager>()

    companion object {
        const val MODULE_MANAGER = "module_manager"
    }

    init {
        /*
            通过Manifest文件，将value值为“module_manager”的key取出
            利用反射获取[IModuleManager]实例
         */
        val metadata = context.packageManager.getApplicationInfo(
            context.packageName,
            PackageManager.GET_META_DATA
        ).metaData
        metadata.keySet().forEach { key ->
            if (metadata[key] == MODULE_MANAGER) {
                mModuleManagers.add(ReflectUtils.reflect(key).newInstance().get<IModuleManager>())
            }
        }
    }

    override fun attachBaseContext(base: Context) {
        mModuleManagers.forEach {
            it.attachBaseContext(base)
        }
    }

    override fun onCreate(application: Application) {
        mModuleManagers.forEach {
            it.onCreate(application)
        }
    }

    override fun onTerminate(application: Application) {
        mModuleManagers.forEach {
            it.onTerminate(application)
        }
    }

}