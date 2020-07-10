package com.martin.module

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.martin.lib_base.basic.BaseActivity
import com.martin.module.databinding.ActivityModuleBinding

@Route(path = "/module/activity")
class ModuleActivity : BaseActivity<ActivityModuleBinding>() {

    override fun layoutId() = R.layout.activity_module

    override fun initOnCreate(savedInstanceState: Bundle?) {
    }
}
