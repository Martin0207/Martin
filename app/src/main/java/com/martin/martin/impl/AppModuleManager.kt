package com.martin.martin.impl

import android.app.Application
import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.alibaba.android.arouter.launcher.ARouter
import com.martin.lib_base.interfaces.IModuleManager
import com.martin.lib_base.BaseLib
import com.martin.martin.ui.main.MainModel
import com.martin.martin.ui.main.MainViewModel
import com.martin.martin.ui.user.login.LoginModel
import com.martin.martin.ui.user.login.LoginViewModel
import com.martin.martin.ui.user.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author：孟凡华
 * @date：2019/9/6 14:27
 */
class AppModuleManager : IModuleManager {

    override fun attachBaseContext(base: Context) {
    }

    override fun onCreate(application: Application) {
        if (BaseLib.debug) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(application)
        initModules()
    }

    override fun onTerminate(application: Application) {

    }

    private fun initModules() {
        BaseLib.modules.add(module {
            viewModel { MainViewModel() }
            single { MainModel() }
            factory { LoginModel() }
            /*
                当构造函数中含有无法通过Koin获取的字段,可以通过该方式来实现
             */
            viewModel { (lifeOwner: LifecycleOwner) ->
                LoginViewModel(get(), get(), lifeOwner)
            }
            viewModel { RegisterViewModel() }
        })
    }

}