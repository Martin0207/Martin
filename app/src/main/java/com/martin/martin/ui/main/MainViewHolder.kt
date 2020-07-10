package com.martin.martin.ui.main

import android.view.View
import androidx.databinding.DataBindingUtil
import com.martin.lib_base.annotation.ItemResID
import com.martin.lib_base.basic.BaseViewHolder
import com.martin.lib_base.expansion.successToast
import com.martin.martin.R
import com.martin.martin.databinding.ItemTextNormalBinding
import com.martin.martin.databinding.ItemTextNormalBindingImpl

@ItemResID(R.layout.item_text_normal)
class MainViewHolder(view: View) :
    BaseViewHolder<String, ItemTextNormalBinding>(view) {

    override fun onBindViewHolder(position: Int, item: Any?) {
        var itemBean = getItemBean(item)
        mBinding?.data = itemBean

        itemView.setOnClickListener {
            successToast("点击 $itemBean")
        }
    }

}