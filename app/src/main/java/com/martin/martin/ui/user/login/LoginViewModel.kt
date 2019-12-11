package com.martin.martin.ui.user.login

import android.content.SharedPreferences
import android.content.res.Resources
import androidx.lifecycle.*
import androidx.work.impl.utils.PreferenceUtils
import com.martin.lib_base.constant.ConstantKey
import com.martin.lib_base.dao.DB
import com.martin.lib_base.expansion.errorToast
import com.martin.lib_base.expansion.loge
import com.martin.lib_base.pojo.entity.UserEntity
import com.martin.lib_base.expansion.successToast
import com.martin.martin.R
import com.martin.martin.ui.main.MainActivity
import dev.DevUtils
import dev.utils.app.KeyBoardUtils
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.get


/**
 * @author：孟凡华
 * @date: 2019 20:02
 *
 */
class LoginViewModel(
    val model: LoginModel,
    val sp: SharedPreferences,
    val lifeOwner: LifecycleOwner
) : ViewModel(),
    /**
     * 如果想要在非Activity/Fragment中使用Koin功能
     * 需要让其实现[KoinComponent]接口
     */
    KoinComponent {

    /**
     * 历史用户列表
     */
    val users: LiveData<List<UserEntity>> by lazy {
        model.users()
    }

    /**
     * 账号
     * 通过[SharedPreferences]获取历史记录
     */
    var account: MutableLiveData<String> = MutableLiveData()

    /**
     * 密码
     * 获取方式同[account]
     */
    var password: MutableLiveData<String> = MutableLiveData()

    init {
        viewModelScope.launch {
            account.value = sp.getString(ConstantKey.ACCOUNT, "")
            password.value = sp.getString(ConstantKey.PASSWORD, "")
        }

        /**
         * 这里主要测试一下注册添加了用户后,这里会不会获取更新通知
         * 实验结果是能够获得更新通知
         */
        users.observe(lifeOwner) {
            if (it.isNotEmpty()) {
                loge("last entity is ${it.last()}")
            }
        }
    }

    /**
     * 登录
     */
    fun login() {
        KeyBoardUtils.closeKeyboard()
        when {
            account.value.isNullOrEmpty() ->
                errorToast(get<Resources>().getString(R.string.input_account))
            password.value.isNullOrEmpty() ->
                errorToast(get<Resources>().getString(R.string.input_password))
            else -> {
                viewModelScope.launch {
                    val data = get<DB>().userDao().query(account.value ?: "")
                    when {
                        data == null -> errorToast(get<Resources>().getString(R.string.account_null))
                        password.value?.equals(data.password) != true -> errorToast(
                            get<Resources>().getString(
                                R.string.password_invalid
                            )
                        )
                        else -> {
                            successToast(get<Resources>().getString(R.string.login_success))
                            sp.edit()
                                .putBoolean(ConstantKey.IS_LOGIN, true)
                                .apply()
                            MainActivity.start(DevUtils.getTopActivity())
                        }
                    }
                }
            }
        }
    }

    override fun toString(): String {
        return "LoginViewModel(account=$account, password=$password)"
    }

}