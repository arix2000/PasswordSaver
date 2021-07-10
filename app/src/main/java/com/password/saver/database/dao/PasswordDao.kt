package com.password.saver.database.dao

import androidx.room.*
import com.password.saver.models.Password
import kotlinx.coroutines.flow.Flow

@Dao
interface PasswordDao {
    @Insert()
    suspend fun insert(password: Password)

    @Delete
    suspend fun delete(password: Password)

    @Query("SELECT * FROM password")
    fun getAllPasswords(): Flow<List<Password>>
}