package com.martin.martin.impl

import android.app.Application
import android.content.Context
import com.martin.lib_base.interfaces.IModuleManager
import com.martin.lib_base.BaseLib
import com.martin.martin.ui.main.MainModel
import com.martin.martin.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author：孟凡华
 * @date：2019/9/6 14:27
 */
class AppModuleManager : IModuleManager {

    override fun attachBaseContext(base: Context) {
        BaseLib.modules.add(module {
            viewModel { MainViewModel() }
            single { MainModel() }
        })
    }

    override fun onCreate(application: Application) {
    }

    override fun onTerminate(application: Application) {

    }

}