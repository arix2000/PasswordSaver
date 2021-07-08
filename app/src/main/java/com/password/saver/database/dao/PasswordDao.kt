package com.password.saver.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.password.saver.models.Password

@Dao
interface PasswordDao {
    @Insert
    fun insert(password: Password)

    @Delete
    fun delete(password: Password)

    @Query("SELECT * FROM password")
    fun getAllPasswords(): List<Password>
}