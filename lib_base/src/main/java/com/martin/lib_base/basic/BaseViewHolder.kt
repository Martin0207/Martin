package com.martin.lib_base.basic

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


abstract class BaseViewHolder<T, B : ViewDataBinding>(view: View) :
    RecyclerView.ViewHolder(view) {

    protected var mBinding: B? = null

    /**
     * 当创建ViewHolder时
     * 在这里创建 mBinding
     */
     fun onCreateViewHolder(){
        mBinding = DataBindingUtil.bind(itemView)
    }

    /**
     * 绑定ViewHolder时
     * 一般在此将ViewModel设置到binding中
     * 并且处理点击事件
     */
    abstract fun onBindViewHolder(position: Int, item: Any?)

    /**
     * 获取实体类
     */
    protected fun getItemBean(item: Any?): T? {
        return try {
            item as T
        } catch (e: Exception) {
            null
        }
    }

}