package com.martin.martin.ui.user.register

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martin.lib_base.dao.DB
import com.martin.lib_base.errorToast
import com.martin.lib_base.loge
import com.martin.lib_base.pojo.entity.UserEntity
import com.martin.lib_base.successToast
import com.martin.martin.R
import dev.DevUtils
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.get

/**
 * @author：孟凡华
 * @date: 2019/11/5 19:38
 *
 */
class RegisterViewModel : ViewModel(), KoinComponent {

    /**
     * LiveData也不需要滥用
     * 再不需要数据的修改监听时,用基本的数据就好了
     */
    var account: String? = null

    var password: String? = null

    var rePassword: String? = null

    var name: String? = null

    var age: String? = null

    /**
     * 注册
     */
    fun register() {
        when {
            account.isNullOrEmpty() ->
                errorToast(get<Resources>().getString(R.string.input_account))
            password.isNullOrEmpty() ->
                errorToast(get<Resources>().getString(R.string.input_password))
            rePassword.isNullOrEmpty() ->
                errorToast(get<Resources>().getString(R.string.two_pwd_incorrect))
            else -> {
                viewModelScope.launch {
                    val user = UserEntity().apply {
                        account = this@RegisterViewModel.account
                        password = this@RegisterViewModel.password
                        name = this@RegisterViewModel.name
                        age = this@RegisterViewModel.age?.toInt()
                    }
                    val result = get<DB>().userDao().addUser(user)
                    loge("添加用户的返回数据:$result")
                    successToast("Register success")
                    /*
                        判定注册成功后,如果最上层Activity依旧是注册页面,则关闭
                        我仅仅想演示一下,不持有Activity的对象及不使用View层的接口回调,依旧可以解决问题
                     */
                    if (DevUtils.getTopActivity()::class.java.simpleName
                        == RegisterActivity::class.java.simpleName
                    ) {
                        DevUtils.getTopActivity().finish()
                    }
                }
            }
        }
    }

}