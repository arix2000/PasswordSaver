package com.password.saver.repositories

import androidx.lifecycle.LiveData
import com.password.saver.database.dao.PasswordDao
import com.password.saver.models.Password
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PasswordsRepository(private val dao: PasswordDao) {
    private val ioScope = CoroutineScope(Dispatchers.IO)

    fun delete(password: Password) {
        ioScope.launch {
            dao.delete(password)
        }
    }

    fun insert(password: Password) {
        ioScope.launch {
            dao.insert(password)
        }
    }

    fun update(password: Password) {
        ioScope.launch {
            dao.update(password)
        }
    }

    fun getPasswords(): LiveData<List<Password>> {
        return dao.getAllPasswords()
    }
}