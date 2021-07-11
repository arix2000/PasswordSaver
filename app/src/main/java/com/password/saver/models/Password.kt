package com.password.saver.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson

@Entity
data class Password(
    val title: String,
    val login: String,
    val password: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        const val PASSWORD_ARGUMENT_KEY = "password"
        val EMPTY = Password("","","")

        fun fromJson(string: String): Password {
            return Gson().fromJson(string, Password::class.java)
        }
    }
}