package com.martin.martin.ui.user.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import com.martin.lib_base.base.BaseActivity
import com.martin.martin.R
import com.martin.martin.databinding.ActivityLoginBinding
import com.martin.martin.ui.user.register.RegisterActivity
import com.xuexiang.xui.widget.popupwindow.popup.XUISimplePopup
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val mViewModel: LoginViewModel by viewModel {
        parametersOf(this, this)
    }

    override fun layoutId() = R.layout.activity_login

    override fun initOnCreate(savedInstanceState: Bundle?) {
        mBinding.viewModel = mViewModel
        top_bar.removeAllActions()
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    fun register(view: View) {
        RegisterActivity.start(this)
    }
}
