package com.password.saver.di

import com.password.saver.features.loginscreen.LoginViewModel
import com.password.saver.features.passwordlist.PasswordsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { LoginViewModel(get()) }

    viewModel { PasswordsViewModel(get()) }
}