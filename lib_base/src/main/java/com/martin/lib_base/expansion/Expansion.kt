package com.martin.lib_base

import android.view.View
import android.widget.Toast
import com.orhanobut.logger.Logger
import com.xuexiang.xui.utils.SnackbarUtils
import com.xuexiang.xui.widget.toast.XToast
import dev.DevUtils
import timber.log.Timber

/********************************************************************************************
 *  Toast & Snack
 *********************************************************************************************/
fun normalToast(msg: String, isShort: Boolean = true) {
    if (isShort) {
        XToast.normal(DevUtils.getContext(), msg, Toast.LENGTH_SHORT).show()
    } else {
        XToast.normal(DevUtils.getContext(), msg, Toast.LENGTH_LONG).show()
    }
}

fun infoToast(msg: String, isShort: Boolean = true) {
    if (isShort) {
        XToast.info(DevUtils.getContext(), msg, Toast.LENGTH_SHORT).show()
    } else {
        XToast.info(DevUtils.getContext(), msg, Toast.LENGTH_LONG).show()
    }
}

fun warningToast(msg: String, isShort: Boolean = true) {
    if (isShort) {
        XToast.warning(DevUtils.getContext(), msg, Toast.LENGTH_SHORT).show()
    } else {
        XToast.warning(DevUtils.getContext(), msg, Toast.LENGTH_LONG).show()
    }
}

fun successToast(msg: String, isShort: Boolean = true) {
    if (isShort) {
        XToast.success(DevUtils.getContext(), msg, Toast.LENGTH_SHORT).show()
    } else {
        XToast.success(DevUtils.getContext(), msg, Toast.LENGTH_LONG).show()
    }
}

fun errorToast(msg: String, isShort: Boolean = true) {
    if (isShort) {
        XToast.error(DevUtils.getContext(), msg, Toast.LENGTH_SHORT).show()
    } else {
        XToast.error(DevUtils.getContext(), msg, Toast.LENGTH_LONG).show()
    }
}

fun normalSnack(msg: String, view: View, isShort: Boolean = true) {
    if (isShort) {
        SnackbarUtils.Short(view, msg).info().show()
    } else {
        SnackbarUtils.Long(view, msg).info().show()
    }
}
fun warningSnack(msg: String, view: View, isShort: Boolean = true) {
    if (isShort) {
        SnackbarUtils.Short(view, msg).warning().show()
    } else {
        SnackbarUtils.Long(view, msg).warning().show()
    }
}
fun confirmSnack(msg: String, view: View, isShort: Boolean = true) {
    if (isShort) {
        SnackbarUtils.Short(view, msg).confirm().show()
    } else {
        SnackbarUtils.Long(view, msg).confirm().show()
    }
}
fun dangerSnack(msg: String, view: View, isShort: Boolean = true) {
    if (isShort) {
        SnackbarUtils.Short(view, msg).danger().show()
    } else {
        SnackbarUtils.Long(view, msg).danger().show()
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