package com.martin.martin.ui.user.register

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import com.martin.lib_base.base.BaseActivity
import com.martin.martin.R
import com.martin.martin.databinding.ActivityRegisterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {

    private val mViewModel: RegisterViewModel by viewModel()

    override fun layoutId() = R.layout.activity_register

    override fun initOnCreate(savedInstanceState: Bundle?) {
        mBinding.viewModel = mViewModel
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, RegisterActivity::class.java))
        }
    }
}
