package com.password.saver.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Password(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val login: String,
    val password: String
)