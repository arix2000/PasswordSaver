package com.password.saver.features.loginscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.password.saver.Constants.EMPTY_STRING
import com.password.saver.repositories.LoginRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository
): ViewModel() {
    private val mainPasswordLiveData by lazy { MutableLiveData<String>() }

    fun getMainPassword(): MutableLiveData<String> {
        viewModelScope.launch {
            repository.getMainPassword().collect {
                mainPasswordLiveData.value = it ?: EMPTY_STRING
            }
        }
        return mainPasswordLiveData
    }

    fun saveMainPassword(mainPassword: String) {
        repository.saveMainPassword(mainPassword)
    }
}