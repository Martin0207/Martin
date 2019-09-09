package com.martin.lib_base.retrofit.cookie

import com.martin.lib_base.retrofit.cookie.CookieStore

import java.util.ArrayList
import java.util.HashMap
import java.util.HashSet

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class CookieJarImpl(private val cookieStore: CookieStore) : CookieJar {
    private val userCookies = HashMap<String, MutableSet<Cookie>>()  //用户手动添加的Cookie

    fun addCookies(cookies: List<Cookie>) {
        for (cookie in cookies) {
            val domain = cookie.domain()
            var domainCookies: MutableSet<Cookie>? = userCookies[domain]
            if (domainCookies == null) {
                domainCookies = HashSet()
                userCookies[domain] = domainCookies
            }
            domainCookies.add(cookie)
        }
    }

    @Synchronized
    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        cookieStore.saveCookies(url, cookies)
    }

    @Synchronized
    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val requestUrlCookies = cookieStore.loadCookies(url)
        val userUrlCookies = userCookies[url.host()]
        val cookieSet = HashSet<Cookie>()
        if (requestUrlCookies != null) cookieSet.addAll(requestUrlCookies)
        if (userUrlCookies != null) cookieSet.addAll(userUrlCookies)
        return ArrayList(cookieSet)
    }
}
