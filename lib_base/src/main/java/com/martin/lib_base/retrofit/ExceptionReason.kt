package com.martin.lib_base.retrofit

/**
 * @author ：Martin
 * @data : 2018/6/22 14:21
 */
enum class ExceptionReason{
    /**
     * 解析数据失败
     */
    PARSE_ERROR,
    /**
     * 网络问题
     */
    BAD_NETWORK,
    /**
     * 连接错误
     */
    CONNECT_ERROR,
    /**
     * 连接超时
     */
    CONNECT_TIMEOUT,

    /**
     * 网络请求在 主线程
     */
    NETWORK_ON_MAIN_THREAD,

    /**
     * 未知错误
     */
    UNKNOWN_ERROR
}