package com.password.saver.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class LoginRepository(
    private val dataStore: DataStore<Preferences>
) {
    fun getMainPassword(): Flow<String?> {
        return dataStore.data.map { it[MAIN_PASSWORD] }
    }

    fun saveMainPassword(mainPassword: String) {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.edit {
                it[MAIN_PASSWORD] = mainPassword
            }
        }
    }

    companion object {
        val MAIN_PASSWORD = stringPreferencesKey("mainPassword")
    }
}