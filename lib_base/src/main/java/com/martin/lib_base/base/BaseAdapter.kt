package com.martin.lib_base.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @author：孟凡华
 * @date：2019/9/6 16:13
 */
class BaseAdapter<T>(
    private val mContext: Context,
    private val mItemLayoutId: Int,
    val items: ArrayList<T>,
    val initItem: (itemView: View, item: T, position: Int) -> Unit
) : RecyclerView.Adapter<BaseAdapter.ViewHolder>() {

    private val mInflater by lazy {
        LayoutInflater.from(mContext)
    }

    /**
     * 刷新列表元素
     * 如果传入null值,则清空列表数据
     */
    fun refreshItems(list: List<T>?) {
        (list ?: listOf()).let {
            items.clear()
            items.addAll(it)
            notifyDataSetChanged()
        }
    }

    /**
     * 添加单个元素
     */
    fun addItem(item: T, index: Int = -1) {
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
    fun addItems(list: List<T>, index: Int = -1) {
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

    fun getItem(position: Int): T? {
        return if (items.size > position) items[position] else null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = mInflater.inflate(mItemLayoutId, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            initItem(holder.itemView, it, position)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}