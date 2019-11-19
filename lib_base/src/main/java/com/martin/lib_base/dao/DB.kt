package com.martin.lib_base.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.martin.lib_base.pojo.entity.UserEntity

/**
 * @author：孟凡华
 * @date：2019/11/4 15:54:38
 * 全局数据库,
 */
@Database(entities = [UserEntity::class], version = 1)
abstract class DB : RoomDatabase() {

    /**
     * 用户数据
     */
    abstract fun userDao(): UserDao

}