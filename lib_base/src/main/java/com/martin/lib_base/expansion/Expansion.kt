package com.martin.lib_base.expansion

import android.view.View
import android.widget.Toast
import com.orhanobut.logger.Logger
import dev.DevUtils
import dev.utils.app.SnackbarUtils
import es.dmoral.toasty.Toasty
import timber.log.Timber

/********************************************************************************************
 *  Toast & Snack
 *********************************************************************************************/
fun normalToast(msg: String, isShort: Boolean = true) {
    if (isShort) {
        Toasty.normal(DevUtils.getContext(),msg,Toast.LENGTH_SHORT).show()
    } else {
        Toasty.normal(DevUtils.getContext(),msg,Toast.LENGTH_LONG).show()
    }
}

fun infoToast(msg: String, isShort: Boolean = true) {
    if (isShort) {
        Toasty.info(DevUtils.getContext(), msg, Toast.LENGTH_SHORT).show()
    } else {
        Toasty.info(DevUtils.getContext(), msg, Toast.LENGTH_LONG).show()
    }
}

fun warningToast(msg: String, isShort: Boolean = true) {
    if (isShort) {
        Toasty.warning(DevUtils.getContext(), msg, Toast.LENGTH_SHORT).show()
    } else {
        Toasty.warning(DevUtils.getContext(), msg, Toast.LENGTH_LONG).show()
    }
}

fun successToast(msg: String, isShort: Boolean = true) {
    if (isShort) {
        Toasty.success(DevUtils.getContext(), msg, Toast.LENGTH_SHORT).show()
    } else {
        Toasty.success(DevUtils.getContext(), msg, Toast.LENGTH_LONG).show()
    }
}

fun errorToast(msg: String, isShort: Boolean = true) {
    if (isShort) {
        Toasty.error(DevUtils.getContext(), msg, Toast.LENGTH_SHORT).show()
    } else {
        Toasty.error(DevUtils.getContext(), msg, Toast.LENGTH_LONG).show()
    }
}

fun snack(msg: String, view: View, isShort: Boolean = true) {
    if (isShort) {
        SnackbarUtils.with(view).showShort(msg)
    } else {
        SnackbarUtils.with(view).showLong(msg)
    }
}

/********************************************************************************************
 *  日志log
 *********************************************************************************************/
fun logd(msg: String, useTimber: Boolean = true) {
    if (useTimber) {
        Timber.d(msg)
    } else {
        Logger.d(msg)
    }
}

fun logi(msg: String, useTimber: Boolean = true) {
    if (useTimber) {
        Timber.i(msg)
    } else {
        Logger.i(msg)
    }
}

fun logw(msg: String, useTimber: Boolean = true) {
    if (useTimber) {
        Timber.w(msg)
    } else {
        Logger.w(msg)
    }
}

fun loge(msg: String, useTimber: Boolean = true) {
    if (useTimber) {
        Timber.e(msg)
    } else {
        Logger.e(msg)
    }
}

/********************************************************************************************
 *  String
 *********************************************************************************************/
fun String?.noNull() = this ?: ""