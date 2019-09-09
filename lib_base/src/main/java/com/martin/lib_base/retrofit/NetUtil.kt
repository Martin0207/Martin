package com.martin.lib_base.retrofit

import com.google.gson.GsonBuilder
import com.martin.lib_base.impl.HttpLogImpl
import com.martin.lib_base.retrofit.cookie.CookieJarImpl
import com.martin.lib_base.retrofit.cookie.CustomCookieStore
import com.martin.lib_base.retrofit.interceptor.HttpCacheInterceptor
import dev.DevUtils
import me.jessyan.progressmanager.ProgressManager
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.GlobalContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

/**
 * @author ：Martin
 * @data : 2018/6/21 15:39
 */
class NetUtil private constructor() {

    var api: Api by Delegates.notNull()

    companion object {
        private val instance: NetUtil = NetUtil()

        fun getApi() = instance.api

        /**
         * 默认超时限制时间
         */
        val DEFAULT_TIMEOUT: Long = GlobalContext.get().koin.getProperty<Long>("timeout") ?: 12000

        const val cacheSize = 1024L * 1024 * 10
    }

    init {
        val logInterceptor = HttpLoggingInterceptor(HttpLogImpl())
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val cacheFile = File(DevUtils.getContext().getCacheDir(), "cache")
        val cache = Cache(cacheFile, cacheSize)
        val builder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout(), TimeUnit.MILLISECONDS)
            .readTimeout(readTimeout(), TimeUnit.MILLISECONDS)
            .addInterceptor(logInterceptor)
            .addNetworkInterceptor(HttpCacheInterceptor())
//            .cache(cache)
        if (needChangeBaseUrl()) {
        }
        if (needMonitorDownload()) {
            ProgressManager.getInstance().with(builder)
        }
        val client = builder
            .cookieJar(CookieJarImpl(CustomCookieStore()))
            .build()
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .serializeNulls()
            .create()
        val retrofit = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(Api.BASE_URL)
            .build()
        api = retrofit.create(Api::class.java)
    }

    /**
     * 是否需要修改baseUrl
     */
    private fun needChangeBaseUrl() = true

    /**
     * 是否需要监听下载进度
     */
    private fun needMonitorDownload() = true

    /**
     * 读取超时时间
     */
    fun readTimeout() = DEFAULT_TIMEOUT

    /**
     * 连接超时时间
     */
    fun connectTimeout() = DEFAULT_TIMEOUT

}