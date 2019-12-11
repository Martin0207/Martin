package com.martin.martin.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.martin.lib_base.base.BaseActivity
import com.martin.lib_base.base.BaseAdapter
import com.martin.lib_base.expansion.normalToast
import com.martin.martin.R
import com.martin.martin.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_text_normal.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mViewModel: MainViewModel by viewModel()

    private val mAdapter: BaseAdapter<String> by lazy {
        BaseAdapter(
            this,
            R.layout.item_text_normal,
            mViewModel.items()
        ) { itemView, item, _ ->
            itemView.tv.text = item
            itemView.setOnClickListener {
                when (item) {
                    "Module" -> {
                        ARouter.getInstance()
                            .build("/module/activity")
                            .navigation()
                    }
                    "Toast" -> {
                    }
                    else -> {
                        normalToast(item)
                    }
                }
            }
        }
    }

    override fun layoutId() = R.layout.activity_main

    override fun initOnCreate(savedInstanceState: Bundle?) {
        mBinding.viewModel = mViewModel

        rv.adapter = mAdapter
        rv.layoutManager = LinearLayoutManager(this)

    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

}
