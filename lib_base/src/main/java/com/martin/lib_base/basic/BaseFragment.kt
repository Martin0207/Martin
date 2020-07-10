package com.martin.lib_base.basic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
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
}