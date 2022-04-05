package com.password.saver.di

import com.password.saver.extensions.dataStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { androidContext().dataStore }
}