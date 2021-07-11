package com.password.saver.features.passwordlist

import androidx.lifecycle.ViewModel
import com.password.saver.models.Password
import com.password.saver.repositories.PasswordsRepository
import kotlinx.coroutines.flow.Flow

class PasswordsViewModel(private val repository: PasswordsRepository): ViewModel() {

    fun delete(password: Password) {
        repository.delete(password)
    }

    fun insert(password: Password) {
        repository.insert(password)
    }

    fun update(password: Password) {
        repository.update(password)
    }

    fun getPasswords(): Flow<List<Password>> {
        return repository.getPasswords()
    }
}