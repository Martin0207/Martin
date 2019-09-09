package com.martin.lib_base.impl

import com.martin.lib_base.BaseLib
import com.orhanobut.logger.AndroidLogAdapter

/**
 * @author ：Martin
 * @data : 2018/6/7 9:22
 */
class LogAdapterImpl : AndroidLogAdapter() {

    /**
     * 是否打印日志
     * 当设置tag为release时,认为该日志在发布版依旧需要打印
     * 其他情况下 , 仅Debug版打印日志
     */
    override fun isLoggable(priority: Int, tag: String?): Boolean {
        return tag?.equals("release") ?: false || BaseLib.debug
    }

}