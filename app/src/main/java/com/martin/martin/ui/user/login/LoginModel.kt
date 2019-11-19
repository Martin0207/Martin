package com.martin.martin.ui.user.login

import androidx.lifecycle.LiveData
import com.martin.lib_base.dao.DB
import com.martin.lib_base.dao.UserDao
import com.martin.lib_base.pojo.entity.UserEntity
import org.koin.core.KoinComponent
import org.koin.core.get

/**
 * @author：孟凡华
 * @date: 2019 20:02
 *
 */
class LoginModel : KoinComponent {

    /**
     * 获取历史用户列表
     * 演示LiveData与Room的相互协作
     */
    fun users(): LiveData<List<UserEntity>> {
        return get<DB>().userDao().queryAll()
    }


}