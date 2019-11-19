package com.martin.lib_base.pojo.entity

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Since
import com.google.gson.annotations.Until

/**
 * @author：孟凡华
 * @date: 2019 17:15
 *
 */
@Entity
class UserEntity {

    /**
     * 账号
     * 使用[PrimaryKey]来指定该数据库类的主键
     */
    @PrimaryKey
    @Since(1.0)
    @NonNull
    var account: String? = null

    /**
     * 密码
     */
    @Since(1.0)
    var password: String? = null

    /**
     * 姓名
     */
    @Since(1.0)
    var name: String? = null

    /**
     * 年龄
     */
    @Since(1.0)
    var age: Int? = null

    /**
     * 如果变量名称与数据库名称不一致
     * 可以使用[SerializedName]来转换
     */
    @SerializedName("description")
    @Since(1.0)
    var desc: String? = null

    override fun toString(): String {
        return "UserEntity(account=$account, password=$password, name=$name, age=$age, desc=$desc)"
    }


}