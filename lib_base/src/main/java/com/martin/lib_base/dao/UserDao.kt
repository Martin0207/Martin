package com.martin.lib_base.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.martin.lib_base.pojo.entity.UserEntity

/**
 * @author：孟凡华
 * @date: 2019 17:19
 *
 */
@Dao
interface UserDao : BaseDao<UserEntity> {

    /**
     * 这里的返回值,是表中的数据个数
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: UserEntity): Long

    @Delete
    suspend fun deleteUser(user: UserEntity): Int

    @Update
    suspend fun updateUser(user: UserEntity): Int

    @Query("SELECT * FROM UserEntity")
    fun queryAll(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM UserEntity WHERE account = :account")
    suspend fun query(account: String): UserEntity?

}

