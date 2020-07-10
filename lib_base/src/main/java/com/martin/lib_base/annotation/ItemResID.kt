package com.martin.lib_base.annotation

import androidx.annotation.IdRes

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ItemResID(val id: Int)