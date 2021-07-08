package com.password.saver

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.password.saver.di.appModule
import com.password.saver.di.databaseModule
import com.password.saver.di.repositoryModule
import com.password.saver.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyPasswordsApp: Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        startKoin {
            androidLogger()
            androidContext(this@MyPasswordsApp)
            modules(listOf(appModule, databaseModule, repositoryModule, viewModelModule))
        }
    }
}