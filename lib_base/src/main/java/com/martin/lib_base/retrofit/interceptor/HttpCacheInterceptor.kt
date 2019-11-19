package com.martin.lib_base.retrofit.interceptor
import dev.utils.app.NetWorkUtils
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author ：Martin
 * @data : 2018/6/21 17:41
 */
class HttpCacheInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        //没网强制从缓存读取

        if (!NetWorkUtils.isConnect()) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build()
        }

        val originalResponse = chain.proceed(request)
        return if (NetWorkUtils.isConnect()) {
            //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
            val cacheControl = request.cacheControl.toString()
            originalResponse.newBuilder()
                .header("Cache-Control", cacheControl)
                .removeHeader("Pragma")
                .build()
        } else {
            originalResponse.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                .removeHeader("Pragma")
                .build()
        }
    }

}