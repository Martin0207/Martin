package com.martin.lib_base.impl

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.martin.lib_base.basic.BaseViewHolder
import com.martin.lib_base.basic.CustomAdapter
import com.martin.lib_base.expansion.loge

/**
 * 加载图片
 */
@BindingAdapter("url")
fun loadImage(view: ImageView, url: String) {
    loge("加载普通url   $url")
}

/**
 * 加载圆角图片
 */
@BindingAdapter("url", "radius")
fun loadRoundImage(iv: ImageView, url: String, radius: Int) {
    loge("加载圆角url  $url   $radius")
}

@BindingAdapter("viewHolder")
fun setAdapter(rv: RecyclerView, holderClass: Class<out BaseViewHolder<*, out ViewDataBinding>>) {
    loge("获取 ViewHolder ${holderClass.name}")

    holderClass.annotations.forEach {
        loge("获取的注解 ${it.javaClass.simpleName}")
    }

    val customAdapter = CustomAdapter(rv.context, holderClass)
    rv.adapter = customAdapter
}

