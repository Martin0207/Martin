package com.martin.martin.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.martin.lib_base.basic.BaseActivity
import com.martin.lib_base.expansion.customAdapter
import com.martin.lib_base.expansion.loge
import com.martin.lib_base.expansion.refresh
import com.martin.martin.R
import com.martin.martin.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mViewModel: MainViewModel by viewModel()

    override fun layoutId() = R.layout.activity_main

    override fun initOnCreate(savedInstanceState: Bundle?) {
        mBinding.viewModel = mViewModel

        mBinding.rv.refresh(mViewModel.items())
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    fun testClick(view: View) {
    }

}
