package com.password.saver.di

import com.password.saver.repositories.LoginRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { LoginRepository(get()) }
}