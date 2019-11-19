package com.martin.martin

import android.content.Context
import android.widget.BaseAdapter

import com.xuexiang.xui.adapter.simple.XUISimpleAdapter
import com.xuexiang.xui.widget.popupwindow.popup.XUIListPopup

import java.util.ArrayList

/**
 * @author：孟凡华
 * @date: 2019/11/5 10:24
 */
class Test : XUIListPopup<Test> {

    constructor(context: Context, direction: Int, adapter: BaseAdapter) : super(
        context,
        direction,
        adapter
    ) {
    }

    constructor(context: Context, adapter: BaseAdapter) : super(context, adapter) {}
}
