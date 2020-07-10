package com.martin.lib_base.basic

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.martin.lib_base.annotation.ItemResID
import com.martin.lib_base.expansion.loge

/**
 * @author：孟凡华
 * @date：2019/9/6 16:13
 */
class CustomAdapter(
    context: Context,
    private val mHolderClass: Class<out BaseViewHolder<*, out ViewDataBinding>>
) : RecyclerView.Adapter<BaseViewHolder<*, out ViewDataBinding>>() {

    private val items: ArrayList<Any> by lazy {
        arrayListOf<Any>()
    }

    private var mItemId: Int? = null


    private val mInflater by lazy {
        LayoutInflater.from(context)
    }

    /**
     * 刷新列表元素
     * 如果传入null值,则清空列表数据
     */
    fun refreshItems(list: List<Any>?) {
        (list ?: listOf()).let {
            items.clear()
            items.addAll(it)
            notifyDataSetChanged()
        }
    }

    /**
     * 添加单个元素
     */
    fun addItem(item: Any, index: Int = -1) {
        if (index in 0 until items.size) {
            items.add(index, item)
            notifyItemRangeChanged(index, items.size - index)
        } else {
            items.add(item)
            notifyItemChanged(items.size - 1)
        }
    }

    /**
     * 添加多个元素
     */
    fun addItems(list: List<Any>, index: Int = -1) {
        //添加数据前的列表长度
        val oldSize = items.size
        if (index in 0 until oldSize) {
            items.addAll(index, list)
            notifyItemRangeChanged(index, items.size - index)
        } else {
            items.addAll(list)
            notifyItemRangeChanged(oldSize, list.size)
        }
    }

    /**
     * 删除单个元素
     */
    fun removeItem(position: Int) {
        val oldSize = items.size
        if (position in 0 until oldSize) {
            items.removeAt(position)
            notifyItemRangeChanged(position, oldSize - position)
        }
    }

    fun getItem(position: Int): Any? {
        return if (items.size > position) items[position] else null
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<*, out ViewDataBinding> {
        if (mItemId == null) {
            mHolderClass.annotations.forEach {
                loge(it.javaClass.simpleName)
            }
            val annotation = mHolderClass.getAnnotation(ItemResID::class.java)
                ?: throw NullPointerException("ViewHolder必须被[ItemResID]注解")

            mItemId = annotation.id
        }
        val inflate = mInflater.inflate(mItemId!!, parent, false)
        val viewHolder = mHolderClass.getConstructor(View::class.java).newInstance(inflate)
        viewHolder.onCreateViewHolder()
        return viewHolder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*, out ViewDataBinding>, position: Int) {
        holder.onBindViewHolder(position, getItem(position))
    }
}