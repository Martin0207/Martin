package com.martin.lib_base.retrofit.cookie

import okhttp3.Cookie
import okhttp3.HttpUrl

interface CookieStore {

    /**
     * 获取当前所有保存的cookie
     */
    val allCookie: List<Cookie>

    /**
     * 保存url对应所有cookie
     */
    fun saveCookies(url: HttpUrl, cookie: List<Cookie>)

    /**
     * 加载url所有的cookie
     */
    fun loadCookies(url: HttpUrl): List<Cookie>

    /**
     * 根据url和cookie移除对应的cookie
     */
    fun removeCookie(url: HttpUrl, cookie: Cookie): Boolean

    /**
     * 根据url移除所有的cookie
     */
    fun removeCookies(url: HttpUrl): Boolean

    /**
     * 移除所有的cookie
     */
    fun removeAllCookie(): Boolean
}
