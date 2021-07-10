package com.password.saver.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Password(
    val title: String,
    val login: String,
    val password: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}