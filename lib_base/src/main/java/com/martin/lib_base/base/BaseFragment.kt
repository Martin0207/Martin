package com.martin.lib_base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import org.greenrobot.eventbus.EventBus
import kotlin.properties.Delegates

/**
 * @Author: martin
 * @CreateDate: 2019/8/14 9:45
 * @Description:
 */
abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    protected var mBinding: T by Delegates.notNull()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (useEventBus() && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
        mBinding = DataBindingUtil.inflate(inflater, layoutId(), container, false)

        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initOnActivityCreated()
    }

    /**
     * 初始化Fragment内容
     */
    abstract fun initOnActivityCreated()

    /**
     * Fragment布局ID
     */
    abstract fun layoutId(): Int

    override fun onDestroyView() {
        super.onDestroyView()
        if (useEventBus() && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    /**
     * 是否使用EventBus
     */
    protected fun useEventBus() = false

}