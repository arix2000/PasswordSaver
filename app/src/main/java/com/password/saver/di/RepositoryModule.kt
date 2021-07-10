package com.password.saver.di

import com.password.saver.repositories.LoginRepository
import com.password.saver.repositories.PasswordsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { LoginRepository(get()) }

    single { PasswordsRepository(get()) }
}