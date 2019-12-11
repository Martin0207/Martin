package com.martin.martin.ui.splash

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import com.martin.lib_base.constant.ConstantKey
import com.martin.martin.ui.main.MainActivity
import com.martin.martin.ui.user.login.LoginActivity
import org.koin.android.ext.android.inject

/**
 * @author：孟凡华
 * @date: 2019 21:09
 *
 */
class SplashActivity : Activity() {

    val sp: SharedPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (sp.getBoolean(ConstantKey.IS_LOGIN, false)) {
            MainActivity.start(this)
        } else {
            LoginActivity.start(this)
        }
    }

}