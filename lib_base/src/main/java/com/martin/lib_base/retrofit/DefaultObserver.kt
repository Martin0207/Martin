package com.martin.lib_base.retrofit

import android.app.Activity
import android.app.Dialog
import android.os.NetworkOnMainThreadException

import com.google.gson.JsonParseException
import com.martin.lib_base.BaseLib
import com.martin.lib_base.R
import com.martin.lib_base.base.BaseResponse
import com.orhanobut.logger.Logger
import com.xuexiang.xui.XUI
import com.xuexiang.xui.widget.dialog.LoadingDialog
import com.xuexiang.xui.widget.toast.XToast
import dev.DevUtils

import org.json.JSONException

import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.text.ParseException

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.OnErrorNotImplementedException
import retrofit2.HttpException

/**
 * @author martin
 */
abstract class DefaultObserver<T : BaseResponse<*>>(
    activity: Activity?,
    isShowLoading: Boolean = true
) : Observer<T> {

    private var mErrorMsgId: Int? = null

    private var mDialog: Dialog? = null

    init {
        activity?.let {
            if (isShowLoading) {
                mDialog = LoadingDialog(it)
            }
        }
    }

    override fun onSubscribe(d: Disposable) {}

    override fun onNext(response: T) {
        if (response.success) {
            onSuccess(response)
        } else {
            onFail(response)
        }
    }

    private fun dismissProgress() {
        mDialog?.dismiss()
        mDialog = null
    }

    /**
     * 请求成功
     *
     * @param response 服务器返回的数据
     */
    abstract fun onSuccess(response: T)

    /**
     * 服务器返回数据，但success为false
     *
     * @param response 服务器返回的数据
     */
    open fun onFail(response: T?) {
        XToast.error(
            DevUtils.getContext(),
            response?.msg ?: DevUtils.getContext().resources.getString(
                mErrorMsgId ?: R.string.response_return_error
            )
        ).show()
    }

    override fun onError(e: Throwable) {
        Logger.e(e.message ?: "服务器返回数据失败")
        if (e is HttpException) {     //   HTTP错误
            onException(ExceptionReason.BAD_NETWORK)
        } else if (e is ConnectException
            || e is UnknownHostException
            || e is OnErrorNotImplementedException
        ) {   //   连接错误
            onException(ExceptionReason.CONNECT_ERROR)
        } else if (e is InterruptedIOException) {   //  连接超时
            onException(ExceptionReason.CONNECT_TIMEOUT)
        } else if (e is JsonParseException
            || e is JSONException
            || e is ParseException
        ) {   //  解析错误
            onException(ExceptionReason.PARSE_ERROR)
        } else if (e is NetworkOnMainThreadException) { //网络请求不可以放在主线程
            onException(ExceptionReason.NETWORK_ON_MAIN_THREAD)
        } else {
            onException(ExceptionReason.UNKNOWN_ERROR)
        }
        onFail(null)
    }

    override fun onComplete() {
        dismissProgress()
    }

    /**
     * 请求异常
     */
    fun onException(reason: ExceptionReason) {
        when (reason) {
            ExceptionReason.CONNECT_ERROR -> mErrorMsgId = R.string.connect_error
            ExceptionReason.CONNECT_TIMEOUT -> mErrorMsgId = R.string.connect_timeout
            ExceptionReason.BAD_NETWORK -> mErrorMsgId = R.string.bad_network
            ExceptionReason.PARSE_ERROR -> mErrorMsgId = R.string.parse_error
            ExceptionReason.NETWORK_ON_MAIN_THREAD -> mErrorMsgId =
                R.string.Network_On_Main_Thread_Exception
            else -> mErrorMsgId = R.string.unknown_error
        }
    }

}
