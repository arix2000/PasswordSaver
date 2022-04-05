package com.password.saver.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.password.saver.models.Password
import kotlinx.coroutines.flow.Flow

@Dao
interface PasswordDao {
    @Insert()
    suspend fun insert(password: Password)

    @Delete
    suspend fun delete(password: Password)

    @Update
    suspend fun update(password: Password)

    @Query("SELECT * FROM password")
    fun getAllPasswords(): LiveData<List<Password>>
}