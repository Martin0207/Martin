package com.martin.lib_base.impl

import com.martin.lib_base.BaseLib
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import timber.log.Timber
import java.nio.charset.Charset

/**
 * @author ：Martin
 * @data : 2018/6/21 16:41
 * @description : 网络请求Logger实例
 */
class HttpLogImpl : HttpLoggingInterceptor.Logger {

    override fun log(message: String) {
        //非Debug情况下，不打印日志
        if (BaseLib.debug) {
            try {
                //如果是Json数据，则以Json格式打印，便于查看
                val message8 =
                    String(message.toByteArray(charset("UTF-8")), Charset.forName("UTF-8"))
                when {
                    message8.startsWith("{") -> Timber.e(JSONObject(message8).toString(2))
                    message8.startsWith("[") -> Timber.e(JSONArray(message8).toString(2))
                    else -> Timber.e(message8)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Timber.e(message)
            }
        }
    }

}