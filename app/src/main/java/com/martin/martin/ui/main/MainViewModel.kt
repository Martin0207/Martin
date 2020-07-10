package com.martin.martin.ui.main

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.martin.module.MainActivity
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * @author：孟凡华
 * @date：2019/9/6 14:07
 */
class MainViewModel : ViewModel(), KoinComponent {

    private val mModel by inject<MainModel>()

    val viewHolderClass = MainViewHolder::class.java

    fun items() = mModel.mainItems()

    fun test() {
    }

}