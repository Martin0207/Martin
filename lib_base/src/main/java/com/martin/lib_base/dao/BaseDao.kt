package com.martin.lib_base.dao

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.room.*


/**
 * @author：孟凡华
 * @date: 2019 19:42
 *
 */
@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: T) //插入单条数据

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(items: List<T>) //插入list数据

    @Delete
    fun delete(item: T) //删除item

    @Update
    fun update(item: T) //更新item

}