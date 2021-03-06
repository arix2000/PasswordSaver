package com.password.saver.features.passwordlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.password.saver.models.Password
import com.password.saver.repositories.PasswordsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.yield

class PasswordsViewModel(private val repository: PasswordsRepository): ViewModel() {

    fun delete(password: Password) {
        repository.delete(password)
    }

    fun add(password: Password) {
        repository.insert(password)
    }

    fun update(password: Password) {
        repository.update(password)
    }

    fun getPasswords(): LiveData<List<Password>> {
        return repository.getPasswords()
    }
}