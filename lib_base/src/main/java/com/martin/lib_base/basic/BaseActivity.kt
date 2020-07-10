package com.martin.lib_base.basic

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
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

}