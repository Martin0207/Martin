package com.martin.lib_base.retrofit

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.martin.lib_base.impl.HttpLogImpl
import com.martin.lib_base.retrofit.adapter.FloatTypeAdapter
import com.martin.lib_base.retrofit.adapter.IntTypeAdapter
import com.martin.lib_base.retrofit.adapter.LongTypeAdapter
import com.martin.lib_base.retrofit.adapter.ObjectTypeAdapter
import com.martin.lib_base.retrofit.cookie.CookieJarImpl
import com.martin.lib_base.retrofit.cookie.CustomCookieStore
import com.martin.lib_base.retrofit.interceptor.HttpCacheInterceptor
import dev.DevUtils
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.GlobalContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

/**
 * @author ：Martin
 * @data : 2018/6/21 15:39
 */
class NetApply private constructor() {

    var api: Api by Delegates.notNull()

    companion object {
        private val instance: NetApply = NetApply()

        fun getApi() = instance.api

        /**
         * 默认超时限制时间
         */
        private val DEFAULT_TIMEOUT: Long =
            GlobalContext.get().koin.getProperty<Int>("timeout")?.toLong() ?: 12000L
    }

    init {
        val logInterceptor = HttpLoggingInterceptor(HttpLogImpl())
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout(), TimeUnit.MILLISECONDS)
            .readTimeout(readTimeout(), TimeUnit.MILLISECONDS)
            .addInterceptor(logInterceptor)

        val client = builder
            .cookieJar(CookieJarImpl(CustomCookieStore()))
            .build()

        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .registerTypeAdapter(Int::class.java, IntTypeAdapter())
            .registerTypeAdapter(Float::class.java, FloatTypeAdapter())
            .registerTypeAdapter(Long::class.java, LongTypeAdapter())
            .registerTypeAdapter(JsonObject::class.java, ObjectTypeAdapter())
            .serializeNulls()
            .create()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        api = retrofit.create(Api::class.java)
    }

    /**
     * 读取超时时间
     */
    fun readTimeout() = DEFAULT_TIMEOUT

    /**
     * 连接超时时间
     */
    fun connectTimeout() = DEFAULT_TIMEOUT

}