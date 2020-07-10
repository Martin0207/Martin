package com.martin.lib_base.basic

import android.text.TextUtils

/**
 * @author martin
 * 基础数据类，包含数据层级的最外层
 */
class BaseResponse<T> {

    var errorCode: Int = 0
    var msg: String = ""
        get() = if (TextUtils.isEmpty(field)) if (success) "网络访问成功" else "网络访问失败" else field
    var data: T? = null
    var success: Boolean = false

    override fun toString(): String {
        return "BaseResponse(errorCode=$errorCode, data=$data, isSuccess=$success)"
    }

}
