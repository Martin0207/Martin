package com.martin.lib_base.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import org.greenrobot.eventbus.EventBus
import kotlin.properties.Delegates

/**
 * @Author: martin
 * @CreateDate: 2019/8/14 9:45
 * @Description:
 */
abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    protected var mBinding: T by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeSetContentView()
        //dataBinding初始化
        mBinding = DataBindingUtil.setContentView(this, layoutId())
        //EventBus注册
        if (useEventBus() && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
        initOnCreate(savedInstanceState)
    }

    /**
     * 设置布局之前
     */
    private fun beforeSetContentView() {}

    /**
     * 布局ID
     */
    abstract fun layoutId(): Int

    /**
     * 初始化Activity内容
     */
    abstract fun initOnCreate(savedInstanceState: Bundle?)

    override fun onResume() {
        /*
            强制设置为竖屏
         */
        if (requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (useEventBus() && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    /**
     * 是否使用EventBus
     */
    protected fun useEventBus() = false

}