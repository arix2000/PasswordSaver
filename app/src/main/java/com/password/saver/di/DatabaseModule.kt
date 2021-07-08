package com.password.saver.di

import com.password.saver.database.AppDatabase
import org.koin.dsl.module

val databaseModule = module {

    single { AppDatabase.getInstance(get()) }

    single { get<AppDatabase>().passwordDao }
}